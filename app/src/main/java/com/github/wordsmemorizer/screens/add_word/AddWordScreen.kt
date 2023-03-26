package com.github.wordsmemorizer.screens.add_word

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.ui.components.AddWordView
import com.github.wordsmemorizer.ui.components.WMTextField
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@Composable
fun AddWordScreen(navController: NavController, viewModel: AddWordViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    Scaffold(topBar = {
        WMTopAppBar("Add Word", navController)
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(elevation = 4.dp, modifier = Modifier.padding(vertical = 16.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        WMTextField(
                            text = state.name,
                            hint = stringResource(id = R.string.input_word),
                            onKeyboardAction = {
                                //TODO start searching
                            },
                            onValueChange = { name ->
                                viewModel.changeName(name)
                            })
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedButton(
                                elevation = ButtonDefaults.elevation(),
                                onClick = { /*TODO*/ }
                            ) {
                                Text(text = stringResource(id = R.string.add_custom_word))
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                elevation = ButtonDefaults.elevation(),
                            ) {
                                Text(text = stringResource(id = R.string.search_in_dictionary))
                            }
                        }
                    }
                }
            }
            item {
                AddWordView(word = state) { wordState ->
                    viewModel.changeWordState(wordState)
                }
            }
            item {
                Button(modifier = Modifier.padding(bottom = 16.dp), onClick = { viewModel.saveWord() }) {
                    Text(text = stringResource(id = R.string.add_to_library), style = MaterialTheme.typography.h5)
                }
            }

        }
    }
}