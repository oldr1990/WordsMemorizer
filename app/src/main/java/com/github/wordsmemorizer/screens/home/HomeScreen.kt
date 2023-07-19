package com.github.wordsmemorizer.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes

object HomeScreen : Routes {
    override val route = "home"
    @Composable
    override fun Builder(
        entry: NavBackStackEntry,
        navController: NavController,
        arguments: String
    ) {
        HomeScreen(navController = navController)
    }

}
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {

}