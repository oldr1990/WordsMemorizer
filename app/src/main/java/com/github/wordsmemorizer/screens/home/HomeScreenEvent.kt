package com.github.wordsmemorizer.screens.home

import com.github.wordsmemorizer.models.Word

sealed interface HomeScreenEvent {
    class AddWord(val word: Word) : HomeScreenEvent
    object OpenSettings : HomeScreenEvent
    class DeleteWord(val word: Word) : HomeScreenEvent
    object MemoryCheck : HomeScreenEvent
    class Answer(val answer: String) : HomeScreenEvent
}