package com.github.wordsmemorizer.screens.add_word

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
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
import com.github.wordsmemorizer.ui.components.AddWordView
import com.github.wordsmemorizer.ui.components.WMSpacer
import com.github.wordsmemorizer.ui.components.WMTextField
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
            Card(elevation = 4.dp) {
                Column(modifier = Modifier.padding(16.dp)) {
                    WMTextField(
                        text = state.name,
                        hint = "Input word to add",
                        onKeyboardAction = {
                                           //TODO start searching
                             },
                        onValueChange = { name ->
                            viewModel.changeName(name)
                        })
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            elevation = ButtonDefaults.elevation(),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(text = "Fill fields by hand")
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            elevation = ButtonDefaults.elevation(),
                        ) {
                            Text(text = "Search")
                        }
                    }
                }
            }
            WMSpacer()
            AddWordView(word = AddWordState(name = "name", sound = "sound", phonetic = "phonetic" ), onValueChanged = {})

        }
    }
}