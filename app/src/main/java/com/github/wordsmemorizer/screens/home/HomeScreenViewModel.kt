package com.github.wordsmemorizer.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.wordsmemorizer.core.BaseViewModel
import com.github.wordsmemorizer.navigation.StringArgument
import com.github.wordsmemorizer.screens.editor.EditorScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<Int>(0, savedStateHandle) {
    override fun onReturnedData(json: String) {
        StringArgument().fromJson(json)?.value?.let {
            showMessage(it)
        }
    }

    fun onScreenEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.AddQuestion -> push(EditorScreen)
        }
    }
}