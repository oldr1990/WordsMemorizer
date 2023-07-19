package com.github.wordsmemorizer.screens.add_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AddWordState>(AddWordState(), savedStateHandle) {


    fun changeSearch(name: String) {
        updateState(state.value.copy(search = name))
    }

    fun saveWord() {
        viewModelScope.launch {

        }
    }
}