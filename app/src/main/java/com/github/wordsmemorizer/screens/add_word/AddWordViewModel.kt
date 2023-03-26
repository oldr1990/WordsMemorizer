package com.github.wordsmemorizer.screens.add_word

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.wordsmemorizer.models.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AddWordState())
    val state = _state.asStateFlow()

    fun addDefinition(definition: String){
        val list = _state.value.definitions + listOf(definition)
        _state.value = _state.value.copy(definitions =  list.toSet().toList())
    }
    fun removeDefinition(definition: String){
        val list = _state.value.definitions.filter { it != definition }
        _state.value = _state.value.copy(definitions =  list)
    }

    fun addLexicalType(lexicalType: String){

    }

    fun removeLexicalType(lexicalType: String){

    }

    fun save(name: String, sound: String, phonetic: String){
            //TODO
    }

    fun changeName(name: String){
        _state.value = _state.value.copy(name = name)
    }
}