package com.github.wordsmemorizer.core

sealed interface WMError {
    class ErrorString(val message: String) : WMError
    class ErrorResource(val resId: Int) : WMError
}