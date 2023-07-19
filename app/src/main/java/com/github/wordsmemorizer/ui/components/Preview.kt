package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.ui.theme.WordsMemorizeTheme


@Composable
fun Preview(content: @Composable () -> Unit) {
    WordsMemorizeTheme() {
        Surface(
            Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}