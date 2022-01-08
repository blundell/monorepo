package modularisation.blundell.library.logging

import android.util.Log
import modularisation.blundell.library.logging.api.Logg

class DebugLogg(private val logTag: String) : Logg {

    override fun d(msg: String) {
        Log.d(logTag, msg)
    }

    override fun e(msg: String) {
        Log.e(logTag, msg)
    }

    override fun e(msg: String, e: Throwable) {
        Log.d(logTag, msg, e)
    }
}
