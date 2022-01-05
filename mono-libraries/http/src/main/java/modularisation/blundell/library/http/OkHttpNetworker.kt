package modularisation.blundell.library.http

import modularisation.blundell.library.http.api.HttpNetworker
import modularisation.blundell.library.http.api.NetworkException
import modularisation.blundell.library.logging.api.Logg
import okhttp3.Request
import okhttp3.Response
import okio.BufferedSource

class OkHttpNetworker(
    private val logg: Logg,
    private val executor: OkHttpExecutor,
) : HttpNetworker {

    /**
     * The client should close the returned BufferedSource
     */
    override fun getBodySource(url: String): Result<Pair<BufferedSource, Long>> {
        val result = getOpenResponse(url)
        if (result.isSuccess) {
            val body = result.getOrThrow().body!!
            val size = body.contentLength()
            return Result.success(Pair(body.source(), size)) //expecting client to close this
        } else {
            return Result.failure(NetworkException(result.exceptionOrNull()!!))
        }
    }

    override fun getContent(url: String): Result<String> {
        val responseResult = getOpenResponse(url)
        if (responseResult.isSuccess) {
            val openResponse = responseResult.getOrThrow()
            openResponse.use {
                val body = it.body!!
                logg.d("content type ${body.contentType()}")
                return Result.success(body.string())
            }
        } else {
            return Result.failure(responseResult.exceptionOrNull()!!)
        }
    }

    override fun isSuccessReachable(url: String): Result<Boolean> {
        val openResponseResult = getOpenResponse(url)
        if (openResponseResult.isSuccess) {
            logg.d("Success reachable network success.")
            val openResponse = openResponseResult.getOrThrow()
            openResponse.use { response ->
                return Result.success(response.isSuccessful).also {
                    logg.d("Success reachable: ${if (it.getOrThrow()) "YES" else "NO"}.")
                }
            }
        } else {
            logg.d("Success reachable network failure or access denied.")
            logg.d("We treat this as 'success' but no audio.")
            return Result.success(false)
        }
    }

    private fun getOpenResponse(url: String): Result<Response> {
        val request = Request.Builder()
            .url(url)
            .build()

        return executor.execute(request)
    }

    override fun getContentLength(url: String): Result<Long> {
        val responseResult = headOpenResponse(url)
        if (responseResult.isSuccess) {
            val response = responseResult.getOrThrow()
            val contentLength = response.headers["Content-Length"]!!.toLong()
            response.close()
            return Result.success(contentLength)
        } else {
            return Result.failure(responseResult.exceptionOrNull()!!)
        }
    }

    override fun getRootUrl(url: String): Result<String> {
        val responseResult = headOpenResponse(url)
        if (responseResult.isSuccess) {
            val headOpenResponse = responseResult.getOrThrow()
            return Result.success(headOpenResponse.use { it.request.url.toString() })
        } else {
            return Result.failure(responseResult.exceptionOrNull()!!)
        }
    }

    private fun headOpenResponse(url: String): Result<Response> {
        val request = Request.Builder()
            .url(url)
            .head()
            .build()

        return executor.execute(request)
    }

}
