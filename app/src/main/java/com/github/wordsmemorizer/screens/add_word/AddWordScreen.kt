package com.github.wordsmemorizer.screens.add_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@Composable
fun AddWordScreen(navController: NavController, viewModel: AddWordViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.padding(16.dp),
        topBar = {
            WMTopAppBar("Add Word", navController)
        }) {
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = {navController.navigate(Routes.ADD_CARD) }) {
                Text(text = "Go")
            }
        }
    }
}