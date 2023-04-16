package com.github.wordsmemorizer.screens.add_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.models.Word
import com.github.wordsmemorizer.room.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AddWordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddWordState())
    val state = _state.asStateFlow()

    fun changeWord(wordState: Word){
        _state.value = _state.value.copy(word = wordState)
    }

    fun changeSearch(name: String){
        _state.value = _state.value.copy(search = name)
    }

    fun searchInDictionary(){
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            delay(1.seconds)
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun saveWord(){
        viewModelScope.launch {
            roomRepository.addWordToLibrary(state.value.word)
        }
    }
}