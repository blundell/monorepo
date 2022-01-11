package com.blundell.tut1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blundell.tut1.ui.theme.Tut1Theme
import modularisation.blundell.library.logging.api.Logg
import modularisation.blundell.library.logging.api.LoggBootstrapper

class MainActivity : ComponentActivity() {

    private val logg: Logg = LoggBootstrapper.getLogger(BuildConfig.DEBUG, "TUT1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logg.d("onCreate")
        val model: MainViewModel by viewModels {
            AppViewModelFactory(this.application)
        }
        model.onStart()
        setContent {
            Tut1Theme {
                val state by (viewModel() as MainViewModel).viewState.observeAsState(ViewState.Idle)
                when (state) {
                    is ViewState.Complete -> {
                        val stateComplete = state as ViewState.Complete
                        Surface(color = MaterialTheme.colors.background) {
                            Text(
                                style = MaterialTheme.typography.h6,
                                text = stateComplete.message,
                            )
                        }
                    }
                    is ViewState.Loading -> {
                        val stateLoading = state as ViewState.Loading
                        Surface(color = MaterialTheme.colors.background) {
                            CircularProgressIndicator()
                            Text(
                                style = MaterialTheme.typography.h3,
                                text = "Loading... ${stateLoading.loadingMessage}",
                            )
                        }
                    }
                    else -> {
                        // TUT do nothing
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Tut1Theme {
        Text(text = "Hello ${"Android"}!")
    }
}
