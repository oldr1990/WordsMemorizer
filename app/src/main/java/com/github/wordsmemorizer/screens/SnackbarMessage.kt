package com.github.wordsmemorizer.screens

sealed class SnackbarMessage(val message: String) {
    class Success(message: String) : SnackbarMessage(message)
    class Error(message: String?) : SnackbarMessage(message ?: "")
    class Warning(message: String) : SnackbarMessage(message)
}
