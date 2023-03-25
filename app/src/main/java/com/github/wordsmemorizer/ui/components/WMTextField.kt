package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun WMTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onKeyboardAction: () -> Unit = {},
    keyboardAction: ImeAction = ImeAction.Done,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        value = text,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(imeAction = keyboardAction),
        keyboardActions = KeyboardActions(onAny = { onKeyboardAction.invoke() }),
        label = {
            Text(text = hint)
        },
        onValueChange = onValueChange
    )
}