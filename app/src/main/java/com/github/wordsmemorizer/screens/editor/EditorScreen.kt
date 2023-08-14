package com.github.wordsmemorizer.screens.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import com.github.wordsmemorizer.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.core.BaseScreen
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.ui.components.WMCard
import com.github.wordsmemorizer.ui.components.WMTextField
import com.github.wordsmemorizer.utils.removeSpecialCharacters

object EditorScreen : Routes {
    override val route = "editor"

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Builder(
        entry: NavBackStackEntry,
        navController: NavController,
        arguments: String
    ) {
        val viewModel: EditorViewModel = hiltViewModel()
        val state = viewModel.state.collectAsState().value
        BaseScreen(
            title = R.string.add_word,
            navController = navController,
            viewModel = viewModel
        ) {
            EditorScreen(viewModel::onEvent, state)
        }
    }
}

@Composable
fun EditorScreen(onEvent: (EditorEvent) -> Unit, state: EditorState) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WMCard() {
            WMTextField(
                text = state.card.answer,
                hint = stringResource(id = R.string.word),
                isError = state.answerError,
                keyboardAction = ImeAction.Next,
                onValueChange = { text ->
                    onEvent(EditorEvent.AnswerChanged(text.removeSpecialCharacters()))
                }
            )

            WMTextField(
                text = state.card.question,
                hint = stringResource(id = R.string.definition),
                isError = state.questionError,
                keyboardAction = ImeAction.Next,
                onValueChange = { text ->
                    onEvent(EditorEvent.QuestionChanged(text.removeSpecialCharacters()))
                }
            )

            WMTextField(
                text = state.card.hint,
                isError = state.hintError,
                hint = stringResource(id = R.string.hint),
                keyboardAction = ImeAction.Next,
                onValueChange = { text ->
                    onEvent(EditorEvent.HintChanged(text.removeSpecialCharacters()))
                }
            )
        }
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { onEvent(EditorEvent.OnSave) }) {
            Text(text = stringResource(id = R.string.add_word), maxLines = 1)
        }
    }

}