package com.github.wordsmemorizer.screens.add_word

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@Composable
fun AddWordScreen(navController: NavController, viewModel: AddWordViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    Scaffold(topBar = {
        WMTopAppBar("Add Word", navController)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.name,
                singleLine = true,
                shape = RoundedCornerShape(10),
                label = {
                    Text(text = "Write your word")
                },
                onValueChange = { name ->
                    viewModel.changeName(name)
                })
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Build, null)
                    Text(text = "Create by yourself")
                }
                Button(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Search, null, tint = Color.White)
                    Text(text = "Search")
                }
            }

            state.definitions.forEach { definition ->
                TextButton(onClick = { viewModel.removeDefinition(definition) }) {
                    Text(text = definition, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}