package com.blundell.tut1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import modularisation.blundell.library.http.api.HttpNetworker
import modularisation.blundell.library.logging.api.Logg

class MainViewModel(
    private val logg: Logg,
    private val httpNetworker: HttpNetworker,
) : ViewModel() {

    val viewState = MutableLiveData<ViewState>(ViewState.Idle)

    fun onStart() {
        logg.d("VM onStart()")
        viewModelScope.launch(Dispatchers.Main) {
            viewState.value = ViewState.Loading("Doing some http background stuff.")
        }
        viewModelScope.launch(Dispatchers.IO) {
            val content = httpNetworker
                .getContent("https://your.westlancs.gov.uk/?uprn=100010662672")
            if (content.isSuccess) {
                val contentHtml = content.getOrThrow()
                try {
                    logg.d("Doing stuff")
                    delay(1000)
                    viewModelScope.launch(Dispatchers.Main) {
                        viewState.value = ViewState.Complete(
                            "Completed stuff",
                        )
                    }
                } catch (ex: Exception) {
                    logg.e("Failed to do stuff", IllegalStateException("Error in [$contentHtml]."))
                    viewModelScope.launch(Dispatchers.Main) {
                        viewState.value = ViewState.Error("Oh no! A reading error occurred, sorry about that.")
                    }
                }
            } else {
                logg.e("Failed to load.", content.exceptionOrNull()!!)
                viewModelScope.launch(Dispatchers.Main) {
                    viewState.value = ViewState.Error("Oh no! A network error occurred, sorry about that.")
                }
            }
        }
    }
}

sealed class ViewState {
    data class Complete(val message: String) : ViewState()
    data class Loading(val loadingMessage: String) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
    object Idle : ViewState()
}
