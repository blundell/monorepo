package modularisation.blundell.library.http.api

import modularisation.blundell.library.http.OkHttpExecutor
import modularisation.blundell.library.http.OkHttpNetworker
import modularisation.blundell.library.logging.api.Logg
import okhttp3.OkHttpClient
import java.time.Duration

object HttpBootstrapper {

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(20))
            .readTimeout(Duration.ofSeconds(20))
            .build()
    }

    fun getHttpNetworker(logg: Logg): HttpNetworker {
        return OkHttpNetworker(logg, OkHttpExecutor(logg, client))
    }

}
