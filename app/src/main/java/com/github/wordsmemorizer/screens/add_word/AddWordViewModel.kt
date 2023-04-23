package com.github.wordsmemorizer.screens.add_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.models.Word
import com.github.wordsmemorizer.network.oxford.OxfordRepository
import com.github.wordsmemorizer.room.RoomRepository
import com.github.wordsmemorizer.screens.BaseViewModel
import com.github.wordsmemorizer.screens.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AddWordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val roomRepository: RoomRepository,
    private val oxfordRepository: OxfordRepository
) : BaseViewModel<AddWordState>(AddWordState()) {

    fun changeWord(wordState: Word) {
        updateState(state.value.copy(word = wordState))
    }

    fun changeSearch(name: String) {
        updateState(state.value.copy(search = name))
    }

    fun searchInDictionary() {
        loading(true)
        viewModelScope.launch {
            val result = oxfordRepository.searchWord(state.value.search)
        loading(false)
        }
    }

    fun saveWord() {
        viewModelScope.launch {
            roomRepository.addWordToLibrary(state.value.word)
        }
    }
}