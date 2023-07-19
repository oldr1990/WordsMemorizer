package com.github.wordsmemorizer.core

sealed class SnackbarMessage {
    class FromString(val message: String) : SnackbarMessage()
    class FromResource(val resource: Int) : SnackbarMessage()
}
