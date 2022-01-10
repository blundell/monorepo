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

    private fun getOpenResponse(url: String): Result<Response> {
        val request = Request.Builder()
            .url(url)
            .build()

        return executor.execute(request)
    }
}
