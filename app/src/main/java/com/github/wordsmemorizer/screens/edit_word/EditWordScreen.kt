package com.github.wordsmemorizer.screens.edit_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.screens.BaseScreen
import com.github.wordsmemorizer.ui.components.WMCard
import com.github.wordsmemorizer.ui.components.WMTextField
import com.github.wordsmemorizer.ui.components.WMTopAppBar
import com.github.wordsmemorizer.utils.removeSpecialCharacters

class EditWordScreen(val id: Int? = null) : Routes {
    override val route: String = "edit_word"

    @Composable
    override fun Builder(entry: NavBackStackEntry, navController: NavController) {
        val viewModel: EditWordViewModel = hiltViewModel()
        viewModel.id = id
        EditWordScreen(navController = navController, viewModel)
    }

}

@Composable
fun EditWordScreen(navController: NavController, viewModel: EditWordViewModel) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    BaseScreen(navController = navController, viewModel = viewModel, scaffoldState) {
        Scaffold(
            topBar = {
                WMTopAppBar(R.string.add_word, navController)
            },
            scaffoldState = scaffoldState
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WMCard() {
                    WMTextField(
                        text = state.word.word,
                        hint = stringResource(id = R.string.word),
                        keyboardAction = ImeAction.Next,
                        isError = state.showFieldError && state.word.word.isEmpty(),
                        onValueChange = { text ->
                            viewModel.onWordChange(state.word.copy(word = text.removeSpecialCharacters()))
                        }
                    )
                    WMTextField(
                        text = state.word.definition,
                        hint = stringResource(id = R.string.definition),
                        isError = state.showFieldError && state.word.definition.isEmpty(),
                        keyboardAction = ImeAction.Next,
                        onValueChange = { text ->
                            viewModel.onWordChange(state.word.copy(definition = text.removeSpecialCharacters()))
                        }
                    )
                    WMTextField(
                        text = state.word.hint,
                        hint = stringResource(id = R.string.hint),
                        keyboardAction = ImeAction.Next,
                        onValueChange = { text ->
                            viewModel.onWordChange(state.word.copy(hint = text.removeSpecialCharacters()))
                        }
                    )
                    WMTextField(
                        text = state.word.phonetic,
                        hint = stringResource(id = R.string.phonetic),
                        onKeyboardAction = {
                            viewModel.saveWord()
                        },
                        onValueChange = { text ->
                            viewModel.onWordChange(state.word.copy(phonetic = text.removeSpecialCharacters()))
                        }
                    )
                }
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = { viewModel.saveWord() }) {
                    Text(text = stringResource(id = R.string.add_word), maxLines = 1)
                }
            }
        }
    }
}