package com.github.wordsmemorizer.screens.comosables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WMBottomAppBar(){
    BottomAppBar(cutoutShape = CircleShape){
            Text(text = "Bottom app bar")
    }
}
