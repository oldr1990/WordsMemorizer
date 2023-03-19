package com.github.wordsmemorizer.screens.add_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@Composable
fun AddWord(navController: NavController) {
    Scaffold(modifier = Modifier,
        topBar = {
            WMTopAppBar("Add Word", navController)
        }) {
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = {navController.navigate(Routes.ADD_CARD.name) }) {
                Text(text = "Go")
            }
        }
    }
}