package modularisation.blundell.library.http.api

interface HttpNetworker {
    fun getContent(url: String): Result<String>
}

class NetworkException(override val cause: Throwable) : Exception(cause) {
    class Timeout(override val cause: Throwable) : Exception(cause)
    class Failure(override val message: String) : Exception(message)
}
