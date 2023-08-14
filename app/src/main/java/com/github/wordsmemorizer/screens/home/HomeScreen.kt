package com.github.wordsmemorizer.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.core.BaseScreen
import com.github.wordsmemorizer.navigation.Routes

object HomeScreen : Routes {
    override val route = "home"

    @ExperimentalMaterial3Api
    @Composable
    override fun Builder(
        entry: NavBackStackEntry,
        navController: NavController,
        arguments: String
    ) {
        val viewModel: HomeScreenViewModel = hiltViewModel()
        val state = viewModel.state.collectAsState()
        BaseScreen(
            title = R.string.app_name,
            navController = navController,
            viewModel = viewModel,
            key = route,
        ) {
            HomeScreen(viewModel::onScreenEvent, state)
        }
    }

}
@Composable
fun HomeScreen( onEvent: (HomeScreenEvent) -> Unit, state: State<Int>) {
    Button(onClick = { onEvent(HomeScreenEvent.AddQuestion) }) {
        Text(text = "Add")
    }
}