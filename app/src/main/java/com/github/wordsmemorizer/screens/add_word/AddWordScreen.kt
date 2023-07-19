package com.github.wordsmemorizer.screens.add_word

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes

object AddWordScreen : Routes {
    override val route = "add_card"
    @Composable
    override fun Builder(
        entry: NavBackStackEntry,
        navController: NavController,
        arguments: String
    ) {
        AddWordScreen(navController = navController)
    }
}
@Composable
fun AddWordScreen(navController: NavController, viewModel: AddWordViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val visibleBottom = remember { mutableStateOf(false) }
    val density = LocalDensity.current
}