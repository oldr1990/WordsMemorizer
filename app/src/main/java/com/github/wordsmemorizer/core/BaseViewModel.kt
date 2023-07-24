package com.github.wordsmemorizer.core



import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.navigation.Argument
import com.github.wordsmemorizer.navigation.Navigation
import com.github.wordsmemorizer.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okio.ByteString.Companion.decodeBase64
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

@HiltViewModel
open class BaseViewModel<T>(initialState: T, savedStateHandle: SavedStateHandle) :
    ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    private val _screenEvent = MutableSharedFlow<ScreenAction>()
    val screenEvent = _screenEvent.asSharedFlow()

    val argument = savedStateHandle.get<String>(Navigation.argKey).orEmpty().decodeBase64()
    fun showMessage(message: String) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenAction.Snackbar(SnackbarMessage.FromString(message)))
        }
    }

    fun showMessage(resource: Int) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenAction.Snackbar(SnackbarMessage.FromResource(resource)))
        }
    }


    fun updateState(state: T) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    fun loading(loading: Boolean) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenAction.Progressbar(loading))
        }
    }

    fun popUp() {
        viewModelScope.launch {
            _screenEvent.emit(ScreenAction.Navigate(NavigationAction.PopUp))
        }
    }

    fun <T> push(route: Routes, arguments: Argument<T>? = null) {
        viewModelScope.launch {
            _screenEvent.emit(
                ScreenAction.Navigate(NavigationAction.Push(route, arguments))
            )
        }
    }
    fun  push(route: Routes) {
        viewModelScope.launch {
            _screenEvent.emit(
                ScreenAction.Navigate(NavigationAction.Push<Any>(route, null))
            )
        }
    }

    fun <T> pushReplacement(
        destination: Routes,
        replacedRoute: Routes? = null,
        arguments: Argument<T>? = null
    ) {
        viewModelScope.launch {
            _screenEvent.emit(
                ScreenAction.Navigate(
                    NavigationAction.PushReplacement(
                        replacedRoute,
                        destination,
                        arguments
                    )
                )
            )
        }
    }

    fun <R> startRequest(
        request: suspend () -> Response<R>,
        customErrorHandler: Boolean = false,
        onError: (WMError) -> Unit = {},
        onSuccess: (R) -> Unit
    ) {
        viewModelScope.launch {
            loading(true)
            try {
                val result = request.invoke()
                loading(false)
                when (result) {

                    is Response.Error -> {
                        val error = errorHandler(result.exception)
                        onError(error)
                        if (customErrorHandler) {
                            return@launch
                        }
                        showError(error)
                    }

                    is Response.Success -> {
                        onSuccess(result.data)
                    }
                }

            } catch (e: java.lang.Exception) {
                loading(false)
                val error = errorHandler(e)
                onError(error)
                if (customErrorHandler) {
                    return@launch
                }
                showError(error)
            }
        }
    }

    private fun showError(error: WMError) {
        when (error) {
            is WMError.ErrorResource -> showMessage(error.resId)
            is WMError.ErrorString -> showMessage(error.message)
        }
    }

    private fun errorHandler(e: Exception): WMError {
        return when (e) {
            is HttpException -> {
                val message = parseError(e)
                if (message.isNotEmpty()) {
                    WMError.ErrorString(message)
                } else {
                    WMError.ErrorResource(R.string.default_error)
                }
            }

            is SSLHandshakeException -> {
                WMError.ErrorResource(R.string.error_sslhandshake)
            }

            is ConnectException -> {
                WMError.ErrorResource(R.string.error_no_internet_connection)
            }

            is UnknownHostException -> {
                WMError.ErrorResource(R.string.error_no_internet_connection)
            }

            else -> {
                if (e.message == null) {
                    WMError.ErrorResource(R.string.default_error)
                } else {
                    WMError.ErrorString(e.message!!)
                }
            }
        }
    }

    fun parseError(e: HttpException): String {
        val json = e.response()?.errorBody()?.string()?.replace("\n", "")
        return JSONObject(json.orEmpty()).getString("message")
            ?: JSONObject(json.orEmpty()).getString("error").orEmpty()
    }
}