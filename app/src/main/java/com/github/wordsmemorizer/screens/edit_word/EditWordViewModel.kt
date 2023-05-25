package com.github.wordsmemorizer.screens.edit_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.models.SimpleWord
import com.github.wordsmemorizer.room.SimpleWordRepository
import com.github.wordsmemorizer.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWordViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: SimpleWordRepository
) : BaseViewModel<EditWordState>(EditWordState()) {
    var id: Int? = null
    fun saveWord(){
        if (state.value.word.isValid) {
            viewModelScope.launch {
                loading(true)
                val word = state.value.word
                if (roomRepository.wordNotExist(word.word)) {
                    roomRepository.addWord(word)
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
            roomRepository.deleteWord(state.value.word)
        }
    }

    fun onWordChange(word: SimpleWord){
        val showFieldError = state.value.showFieldError && !state.value.word.isValid
        updateState(state.value.copy(word = word, showFieldError = showFieldError))
    }

}
