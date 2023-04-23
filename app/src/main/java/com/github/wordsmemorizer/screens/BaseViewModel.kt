package com.github.wordsmemorizer.screens


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.navigation.Routes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel<T>(private val initialState: T) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _screenEvent = MutableSharedFlow<ScreenEvent>()
    val screenEvent = _screenEvent.asSharedFlow()

    fun showMessage(message: String) {
        viewModelScope.launch {
            _screenEvent.emit(ScreenEvent.Snackbar(message))
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

    fun  navigate(route: Routes<Any>, arguments: Any?) {
        viewModelScope.launch {
            _screenEvent.emit(
                ScreenEvent.Navigate(NavigationAction.GoTo(route, arguments))
            )
        }
    }
}