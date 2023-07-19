package com.github.wordsmemorizer.core


import com.github.wordsmemorizer.navigation.Argument
import com.github.wordsmemorizer.navigation.Routes

sealed class ScreenEvent {
    class Snackbar(val message: SnackbarMessage) : ScreenEvent()
    class Navigate(val action: NavigationAction) : ScreenEvent()
    class Progressbar(val isLoading: Boolean) : ScreenEvent()
}


sealed class NavigationAction {
    class PushReplacement<T>(
        val replacedRoute: Routes?,
        val destination: Routes,
        val arguments: Argument<T>? = null
    ) : NavigationAction()

    class Push<T>(val route: Routes, val arguments: Argument<T>? = null) : NavigationAction()
    object PopUp : NavigationAction()
}