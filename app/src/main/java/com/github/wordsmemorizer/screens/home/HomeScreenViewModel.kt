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
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.AddWord -> {
                viewModelScope.launch {
                //   dao.upsertWord(event.word)
                }
            }
            is HomeScreenEvent.Answer -> {

            }
            is HomeScreenEvent.DeleteWord -> {
                viewModelScope.launch {
                   // dao.deleteWord(event.word)
                }
            }
            HomeScreenEvent.MemoryCheck -> {}
            HomeScreenEvent.OpenSettings -> {}
        }
    }

    fun addItem(item: Int){
        _state.update { _state.value + item }
    }
}