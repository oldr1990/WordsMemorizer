package com.github.wordsmemorizer.screens.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.core.BaseViewModel
import com.github.wordsmemorizer.models.Flashcard
import com.github.wordsmemorizer.navigation.IntArgument
import com.github.wordsmemorizer.navigation.StringArgument
import com.github.wordsmemorizer.room.FlashcardRepository
import com.github.wordsmemorizer.screens.home.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val repository: FlashcardRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<EditorState>(EditorState(), savedStateHandle) {
    private var card: Flashcard = Flashcard()

    init {
        argument?.let {
            IntArgument().fromJson(it)?.let {
                viewModelScope.launch {
                    val initial = repository.getFlashcard(it.value)
                    initial?.let { flashcard ->
                        updateCard(flashcard)
                    }
                }
            }
        }
    }

    fun onEvent(event: EditorEvent) = when (event) {
        is EditorEvent.AnswerChanged -> {
            updateCard(state.value.card.copy(answer = event.answer))
        }
        is EditorEvent.QuestionChanged -> {
            updateCard(state.value.card.copy(question = event.question))
        }
        is EditorEvent.HintChanged -> {
            updateCard(state.value.card.copy(hint = event.hint))
        }
        EditorEvent.OnSave -> onSave()
    }

    private fun onSave() {
        if (card.isValid) {
            viewModelScope.launch {
                try {
                    repository.addFlashcard(card)
                    popUpWithResult(HomeScreen.route, StringArgument("Success!"))
                } catch (e: Exception) {
                    showError(e)
                }
            }
        } else {
            updateState(
                state.value.copy(
                    answerError = card.answer.isEmpty(),
                    questionError = card.question.isEmpty()
                )
            )
        }
    }

    private fun updateCard(flashcard: Flashcard) {
        card = flashcard
        val oldState = state.value
        updateState(
            oldState.copy(
                card = flashcard,
                answerError = oldState.answerError && card.answer.isEmpty(),
                questionError = oldState.questionError && card.question.isEmpty()
            )
        )
    }

}