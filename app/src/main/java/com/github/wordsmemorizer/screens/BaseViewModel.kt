package com.github.wordsmemorizer.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.network.Response
import com.github.wordsmemorizer.utils.parseError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


open class BaseViewModel<T>(private val initialState: T) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _screenEvent = MutableSharedFlow<ScreenEvent>()
    val screenEvent = _screenEvent.asSharedFlow()

    fun showMessage(message: String) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenEvent.Snackbar(SnackbarMessage.FromString(message)))
        }
    }

    fun showMessage(resource: Int) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenEvent.Snackbar(SnackbarMessage.FromResource(resource)))
        }
    }


    fun updateState(state: T) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    fun loading(loading: Boolean) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenEvent.Progressbar(loading))
        }
    }

    fun popUp() {
        viewModelScope.launch {
            _screenEvent.emit(ScreenEvent.Navigate(NavigationAction.PopUp))
        }
    }

    fun navigate(route: Routes<Any>, arguments: Any?) {
        viewModelScope.launch {
            _screenEvent.emit(
                ScreenEvent.Navigate(NavigationAction.GoTo(route, arguments))
            )
        }
    }

    fun <R> startRequest(
        request: suspend () -> Response<R>,
        onError: (Exception) -> Unit = {},
        onSuccess: (R) -> Unit
    ) {
        viewModelScope.launch {
            loading(true)
            try {
                val result = request.invoke()
                loading(false)
                when (result) {
                    is Response.Error -> {
                        onError(result.exception)
                        errorHandler(result.exception)
                    }
                    is Response.Success -> {
                        onSuccess(result.data)
                    }
                }

            } catch (e: java.lang.Exception) {
                loading(false)
                errorHandler(e)
            }
        }
    }

    fun errorHandler(e: Exception) {
        when (e) {
            is HttpException -> {
                val message = parseError(e)
                if (message.isNotEmpty()) {
                    showMessage(message)
                } else {
                    showMessage(R.string.default_error)
                }
            }
            is SSLHandshakeException -> {
                showMessage(R.string.error_sslhandshake)
            }
            is ConnectException -> {
                showMessage(R.string.error_no_internet_connection)
            }
            is UnknownHostException -> {
                showMessage(R.string.error_no_internet_connection)
            }
            else -> {
                if (e.message == null) {
                    showMessage(R.string.default_error)
                } else {
                    showMessage(e.message!!)
                }
            }
        }
    }
}