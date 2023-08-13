package com.github.wordsmemorizer.screens.editor

sealed interface EditorEvent {
    class QuestionChanged(val question: String) : EditorEvent
    class AnswerChanged(val answer: String) : EditorEvent
    class HintChanged(val hint: String) : EditorEvent
    object OnSave : EditorEvent
}