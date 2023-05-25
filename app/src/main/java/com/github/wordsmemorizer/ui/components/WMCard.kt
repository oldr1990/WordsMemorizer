package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WMCard(modifier: Modifier = Modifier,  content: @Composable ()-> Unit) {
    Card(elevation = 4.dp, modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}