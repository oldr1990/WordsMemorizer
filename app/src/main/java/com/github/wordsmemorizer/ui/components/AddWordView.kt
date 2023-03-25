package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.screens.add_word.AddWordState

@Composable
fun AddWordView(
    modifier: Modifier = Modifier,
    word: AddWordState,
    onValueChanged: (AddWordState) -> Unit,
) {
    Card(elevation = 4.dp) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WMTextField(text = word.name, hint = "Word", onValueChange = {
                onValueChanged(word.copy(name = it))
            })
            WMTextField(text = word.phonetic, hint = "Phonetic", onValueChange = {
                onValueChanged(word.copy(phonetic = it))
            })
            WMTextField(text = word.sound, hint = "Link to Sound", onValueChange = {
                onValueChanged(word.copy(phonetic = it))
            })
        }
    }
}