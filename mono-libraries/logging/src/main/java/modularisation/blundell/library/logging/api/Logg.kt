package modularisation.blundell.library.logging.api

interface Logg {
    fun d(msg: String)
    fun e(msg: String)
    fun e(msg: String, e: Throwable)
}
