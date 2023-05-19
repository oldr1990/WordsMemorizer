package com.github.wordsmemorizer.screens.add_simple_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

class EditWordScreen(val id: Int? = null):Routes {
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
            Column(modifier = Modifier
                .padding(it)
                .padding(16.dp)) {
                WMCard() {
                    WMTextField(
                        text = state.word,
                        hint = stringResource(id = R.string.input_word),
                        onKeyboardAction = {
                           // viewModel.changeSearch(state.search)
                        },
                        onValueChange = { search ->
                            viewModel.onWordChanged(search.removeSpecialCharacters())
                        }
                    )
                    WMTextField(
                        text = state.answer,
                        hint = stringResource(id = R.string.input_word),
                        onKeyboardAction = {
                           // viewModel.changeSearch(state.search)
                        },
                        onValueChange = { text ->
                            viewModel.onAnswerChanged(text.removeSpecialCharacters())
                        }
                    )
                    WMTextField(
                        text = state.answer,
                        hint = stringResource(id = R.string.input_word),
                        onKeyboardAction = {
                           // viewModel.changeSearch(state.search)
                        },
                        onValueChange = { text ->
                            viewModel.onAnswerChanged(text.removeSpecialCharacters())
                        }
                    )
                }
            }
        }
    }
}