package com.github.wordsmemorizer.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<Int>())
    val state: StateFlow<List<Int>> = _state

    fun addItem(item: Int){
        _state.update { _state.value + item }
    }
}