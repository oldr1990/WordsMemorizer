package com.github.wordsmemorizer.screens.add_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddWordState())
    val state = _state.asStateFlow()

    fun changeWordState(wordState: AddWordState){
        _state.value = wordState
    }

    fun changeName(name: String){
        _state.value = _state.value.copy(name = name)
    }

    fun saveWord(){

    }
}