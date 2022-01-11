package com.blundell.tut1

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import modularisation.blundell.library.logging.api.LoggBootstrapper

class AppViewModelFactory(
    private val application: Application,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val logg = LoggBootstrapper.getLogger(BuildConfig.DEBUG, "TUT1")
        @Suppress("UNCHECKED_CAST")
        return MainViewModel(
            logg,
        ) as T
    }
}
