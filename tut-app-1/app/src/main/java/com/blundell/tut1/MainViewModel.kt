package com.blundell.tut1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import modularisation.blundell.library.logging.api.Logg

class MainViewModel(
    private val logg: Logg,
) : ViewModel() {

    val viewState = MutableLiveData<ViewState>(ViewState.Idle)

    fun onStart() {
        logg.d("VM onStart()")
        viewModelScope.launch(Dispatchers.Main) {
            viewState.value = ViewState.Loading("Doing some background world creating stuff.")
        }
        viewModelScope.launch(Dispatchers.IO) {
            logg.d("Doing background stuff.")
            delay(2000)
            viewModelScope.launch(Dispatchers.Main) {
                viewState.value = ViewState.Complete("Hello World!")
            }
            logg.d("Background stuff finished.")
        }
    }
}

sealed class ViewState {
    data class Complete(val message: String) : ViewState()
    data class Loading(val loadingMessage: String) : ViewState()
    object Idle : ViewState()
}
