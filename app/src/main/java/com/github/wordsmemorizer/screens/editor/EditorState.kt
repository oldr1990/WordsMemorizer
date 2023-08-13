package com.github.wordsmemorizer.screens.editor

import com.github.wordsmemorizer.core.UiState
import com.github.wordsmemorizer.models.Flashcard


data class EditorState(
   val card: Flashcard = Flashcard(),
   val questionError: Boolean = false,
   val answerError: Boolean = false,
   val hintError: Boolean = false,
   val uiState: UiState<Unit>  = UiState.Loading()
)
