package com.github.wordsmemorizer.screens.edit_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.models.Question
import com.github.wordsmemorizer.room.QuestionRepository
import com.github.wordsmemorizer.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWordViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: QuestionRepository
) : BaseViewModel<EditWordState>(EditWordState(), savedStateHandle) {
    var id: Int? = null
    fun saveWord(){
        if (state.value.word.isValid) {
            viewModelScope.launch {
                loading(true)
                val word = state.value.word
                if (roomRepository.doesQuestionExist(word.answer)) {
                    roomRepository.addQuestion(word)
                    showMessage(R.string.word_added)
                    popUp()
                } else {
                    showMessage(R.string.error_word_exist)
                }
            }
        } else {
            showMessage(R.string.error_empty_fields)
            updateState(state.value.copy(showFieldError = true))
        }
    }

    fun deleteWord(){
        viewModelScope.launch {
            roomRepository.deleteQuestion(state.value.word)
        }
    }

    fun onWordChange(word: Question){
        val showFieldError = state.value.showFieldError && !state.value.word.isValid
        updateState(state.value.copy(word = word, showFieldError = showFieldError))
    }

}
