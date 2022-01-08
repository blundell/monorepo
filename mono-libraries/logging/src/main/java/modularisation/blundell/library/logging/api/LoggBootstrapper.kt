package modularisation.blundell.library.logging.api

import modularisation.blundell.library.logging.DebugLogg
import modularisation.blundell.library.logging.ProductionLogg

object LoggBootstrapper {

    fun getLogger(buildConfigDebug: Boolean = false, logTag: String): Logg {
        if (buildConfigDebug) {
            return DebugLogg(logTag)
        } else {
            return ProductionLogg()
        }
    }

}
