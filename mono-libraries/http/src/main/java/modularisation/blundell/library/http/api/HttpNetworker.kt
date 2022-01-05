package modularisation.blundell.library.http.api

import okio.BufferedSource

interface HttpNetworker {
    fun getBodySource(url: String): Result<Pair<BufferedSource, Long>>
    fun getContentLength(url: String): Result<Long>
    fun getContent(url: String): Result<String>
    fun isSuccessReachable(url: String): Result<Boolean>
    fun getRootUrl(url: String): Result<String>
}

class NetworkException(override val cause: Throwable) : Exception(cause) {
    class Timeout(override val cause: Throwable) : Exception(cause)
    class Failure(override val message: String) : Exception(message)
}
