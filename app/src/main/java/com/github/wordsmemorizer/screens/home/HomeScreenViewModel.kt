package com.github.wordsmemorizer.screens.home

import androidx.lifecycle.SavedStateHandle
import com.github.wordsmemorizer.core.BaseViewModel
import com.github.wordsmemorizer.screens.editor.EditorScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<Int>(0, savedStateHandle) {
        fun onScreenEvent(event: HomeScreenEvent){
            when(event){
                HomeScreenEvent.AddQuestion -> push(EditorScreen)
            }
        }
}