package modularisation.blundell.library.logging

import android.util.Log
import modularisation.blundell.library.logging.api.Logg

class DebugLogg : Logg {

    override fun d(msg: String) {
        Log.d(TAG, msg)
    }

    override fun e(msg: String) {
        Log.e(TAG, msg)
    }

    override fun e(msg: String, e: Throwable) {
        Log.d(TAG, msg, e)
    }

    companion object {
        private const val TAG = "SHAPP"
    }
}
