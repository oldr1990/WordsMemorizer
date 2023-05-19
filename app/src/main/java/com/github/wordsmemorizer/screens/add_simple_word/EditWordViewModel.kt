package com.github.wordsmemorizer.screens.add_simple_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.models.SimpleWord
import com.github.wordsmemorizer.room.SimpleWordRepository
import com.github.wordsmemorizer.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditWordViewModel@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: SimpleWordRepository
) : BaseViewModel<SimpleWord>(SimpleWord()) {
    var id: Int? = null
    fun saveWord(){
        viewModelScope.launch {
            if (roomRepository.wordNotExist(state.value.word)) {
                roomRepository.addWord(state.value)
            } else {
                showMessage("Word already exist")
            }
        }
    }

    fun deleteWord(){
        viewModelScope.launch {
            roomRepository.deleteWord(state.value)
        }
    }

    fun onWordChanged(word: String){
        viewModelScope.launch{
           updateState(state.value.copy(word = word))
        }
    }
    fun onAnswerChanged(answer: String){
        viewModelScope.launch{
           updateState(state.value.copy(answer = answer))
        }
    }
}
