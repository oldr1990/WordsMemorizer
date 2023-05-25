package com.github.wordsmemorizer.screens.add_word

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.ui.components.AddWordView
import com.github.wordsmemorizer.ui.components.WMProgressBar
import com.github.wordsmemorizer.ui.components.WMTextField
import com.github.wordsmemorizer.ui.components.WMTopAppBar
import com.github.wordsmemorizer.utils.removeSpecialCharacters

@Composable
fun AddWordScreen(navController: NavController, viewModel: AddWordViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    val visibleBottom = remember { mutableStateOf(false) }
    val density = LocalDensity.current
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
                            text = state.search,
                            hint = stringResource(id = R.string.input_word),
                            onKeyboardAction = {
                                viewModel.changeSearch(state.search)
                            },
                            onValueChange = { search ->
                                viewModel.changeSearch(search.removeSpecialCharacters())
                            },
                            maximumCharacters = 50,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedButton(
                                elevation = ButtonDefaults.elevation(),
                                onClick = { visibleBottom.value = true }
                            ) {
                                Text(text = stringResource(id = R.string.add_custom_word))
                            }
                            Button(
                                onClick = { viewModel.searchInDictionary() },
                                elevation = ButtonDefaults.elevation(),
                            ) {
                                Text(text = stringResource(id = R.string.search_in_dictionary))
                            }
                        }
                    }
                }
            }
            item {
                AnimatedVisibility(
                    visible = visibleBottom.value,
                    enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically(
                        expandFrom = Alignment.Top
                    ) + fadeIn(
                        initialAlpha = 0.3f
                    ),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AddWordView(word = state.word) { wordState ->
                            viewModel.changeWord(wordState)
                        }
                        Button(
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = { viewModel.saveWord() }) {
                            Text(
                                text = stringResource(id = R.string.add_to_library),
                                style = MaterialTheme.typography.h5
                            )
                        }
                    }
                }
            }
        }
        if (state.isLoading) WMProgressBar()
    }
}