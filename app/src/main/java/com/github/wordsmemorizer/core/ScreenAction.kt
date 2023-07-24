package com.github.wordsmemorizer.core


import com.github.wordsmemorizer.navigation.Argument
import com.github.wordsmemorizer.navigation.Routes

sealed class ScreenAction {
    class Snackbar(val message: SnackbarMessage) : ScreenAction()
    class Navigate(val action: NavigationAction) : ScreenAction()
    class Progressbar(val isLoading: Boolean) : ScreenAction()
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