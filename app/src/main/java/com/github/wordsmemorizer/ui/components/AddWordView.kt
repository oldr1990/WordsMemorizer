package com.github.wordsmemorizer.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.models.Flashcard
import com.github.wordsmemorizer.utils.removeSpecialCharacters

@Composable
fun AddWordView(
    modifier: Modifier = Modifier,
    flashcard: Flashcard,
    onValueChanged: (Flashcard) -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WMTextField(
                text = flashcard.question,
                hint = stringResource(id = R.string.input_word),
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(flashcard.copy(question = it.removeSpecialCharacters()))
                },
            )
            WMTextField(
                text = flashcard.answer,
                hint = stringResource(id = R.string.input_word),
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(flashcard.copy(answer = it.removeSpecialCharacters()))
                },
            )
            WMTextField(
                text = flashcard.hint,
                hint = stringResource(id = R.string.input_word),
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(flashcard.copy(hint = it.removeSpecialCharacters()))
                },
            )
        }
    }
}
