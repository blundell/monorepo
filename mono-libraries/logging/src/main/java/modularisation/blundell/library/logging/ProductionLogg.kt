package modularisation.blundell.library.logging

import modularisation.blundell.library.logging.api.Logg

class ProductionLogg : Logg {

    override fun d(msg: String) {
        // don't log
    }

    override fun e(msg: String) {
        // don't log
    }

    override fun e(msg: String, e: Throwable) {
        // don't log
    }
}
