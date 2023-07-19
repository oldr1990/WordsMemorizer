package com.github.wordsmemorizer.screens.edit_word


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import com.github.wordsmemorizer.*
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.core.BaseScreen
import com.github.wordsmemorizer.ui.components.WMCard
import com.github.wordsmemorizer.ui.components.WMTextField
import com.github.wordsmemorizer.utils.removeSpecialCharacters


object EditWordScreen : Routes {
    override val route: String = "edit_word"

    @Composable
    override fun Builder(
        entry: NavBackStackEntry,
        navController: NavController,
        arguments: String
    ) {
        val viewModel: EditWordViewModel = hiltViewModel()
        EditWordScreen(navController = navController, viewModel)
    }

}

@Composable
fun EditWordScreen(navController: NavController, viewModel: EditWordViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    BaseScreen(title = R.string.add_word, navController = navController, viewModel = viewModel) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WMCard() {
                WMTextField(
                    text = state.word.answer,
                    hint = stringResource(id = R.string.word),
                    keyboardAction = ImeAction.Next,
                    isError = state.showFieldError && state.word.answer.isEmpty(),
                    onValueChange = { text ->
                        viewModel.onWordChange(state.word.copy(answer = text.removeSpecialCharacters()))
                    }
                )
                WMTextField(
                    text = state.word.question,
                    hint = stringResource(id = R.string.definition),
                    isError = state.showFieldError && state.word.question.isEmpty(),
                    keyboardAction = ImeAction.Next,
                    onValueChange = { text ->
                        viewModel.onWordChange(state.word.copy(question = text.removeSpecialCharacters()))
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
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { viewModel.saveWord() }) {
                Text(text = stringResource(id = R.string.add_word), maxLines = 1)
            }
        }
    }
}