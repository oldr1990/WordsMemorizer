package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.models.LexicalCategories
import com.github.wordsmemorizer.screens.add_word.AddWordState

@OptIn(ExperimentalMaterialApi::class)
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
            WMTextField(
                text = word.name,
                hint = "Word",
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(word.copy(name = it))
                })
            WMTextField(
                text = word.phonetic,
                keyboardAction = ImeAction.Next,
                hint = "Phonetic",
                onValueChange = {
                    onValueChanged(word.copy(phonetic = it))
                })
            WMTextField(text = word.sound, hint = "Link to Sound", onValueChange = {
                onValueChanged(word.copy(phonetic = it))
            })
            Text(text = "Lexical Categories")
            LazyRow(modifier = Modifier.padding(bottom = 16.dp)) {
                items(LexicalCategories.values()) {
                    Chip(
                        modifier = Modifier.padding(end = if (it.ordinal == LexicalCategories.values().size -1) 0.dp else 16.dp),
                        onClick = { onValueChanged(word.copy(lexicalTypes = word.lexicalTypes + it)) },
                        enabled = word.lexicalTypes.contains(it)
                    ) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}