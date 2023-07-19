package com.github.wordsmemorizer.core

sealed interface UiState<T> {
    class Loading<L> : UiState<L>
    class Error<L>(val error: String) : UiState<L>
    class Success<L>(val value: L) : UiState<L>
}