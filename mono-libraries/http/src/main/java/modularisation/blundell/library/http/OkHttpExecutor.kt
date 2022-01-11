package modularisation.blundell.library.http

import modularisation.blundell.library.http.api.NetworkException
import modularisation.blundell.library.http.api.NetworkException.Failure
import modularisation.blundell.library.http.api.NetworkException.Timeout
import modularisation.blundell.library.logging.api.Logg
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InterruptedIOException
import java.net.SocketTimeoutException

class OkHttpExecutor(
    private val logg: Logg,
    private val client: OkHttpClient,
) {

    /**
     * TODO retries
     */
    internal fun execute(request: Request): Result<Response> {
        logg.d("Executing request [${request.method}/${request.url}]")
        try {
            val response = this.client.newCall(request).execute()
            if (response.isSuccessful) {
                return Result.success(response)
            } else {
                logg.e("Network exception [${response.code}].")
                return Result.failure(NetworkException(Failure("${response.code}/${response.message}")))
            }
        } catch (e: SocketTimeoutException) {
            logg.e("Socket exception.")
            return Result.failure(NetworkException(Timeout(e)))
        } catch (e: InterruptedIOException) {
            logg.e("Interrupted exception.")
            return Result.failure(NetworkException(Timeout(e)))
        } catch (e: Exception) {
            logg.e("Catch all exception.")
            return Result.failure(NetworkException(Timeout(e)))
        }
    }

}
