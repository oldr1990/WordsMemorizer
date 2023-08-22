package com.github.wordsmemorizer.screens.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.core.BaseScreen
import com.github.wordsmemorizer.navigation.Argument
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.navigation.push
import com.github.wordsmemorizer.ui.components.BottomNavData
import com.github.wordsmemorizer.ui.components.WMBottomAppBar

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
            bottomBar = { WMBottomAppBar(BottomNavData.Home){ navController.push<Argument<*>>(it.route)} },
            key = route,
        ) {
            HomeScreen(viewModel::onScreenEvent, state)
        }
    }

}

@Composable
fun HomeScreen(onEvent: (HomeScreenEvent) -> Unit, state: State<Int>) {
    Button(onClick = { onEvent(HomeScreenEvent.AddQuestion) }) {
        Text(text = "Add")
    }
}