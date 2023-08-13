package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.screens.editor.EditorScreen
import com.github.wordsmemorizer.screens.home.HomeScreen


interface Routes {
    val route: String

    @Composable
    fun Builder(entry: NavBackStackEntry, navController: NavController, arguments: String)
}



val navigation = listOf(
    HomeScreen,
    EditorScreen,
)
