package com.github.wordsmemorizer.screens


import com.github.wordsmemorizer.navigation.Routes

sealed class ScreenEvent {
    class Snackbar(val message: String) : ScreenEvent()
    class Navigate(val action: NavigationAction) : ScreenEvent()
    class Progressbar(val isLoading: Boolean) : ScreenEvent()
}

sealed class NavigationAction{
     class GoTo (val route: Routes<Any>, val arguments: Any?): NavigationAction()
    object PopUp: NavigationAction()
}