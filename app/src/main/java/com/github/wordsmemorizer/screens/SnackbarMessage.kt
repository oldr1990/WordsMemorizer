package com.github.wordsmemorizer.screens

sealed class SnackbarMessage {
    class FromString(val message: String) : SnackbarMessage()
    class FromResource(val resource: Int) : SnackbarMessage()
}
