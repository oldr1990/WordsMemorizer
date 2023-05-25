package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.screens.edit_word.EditWordScreen
import com.github.wordsmemorizer.screens.add_word.AddWordRoute
import com.github.wordsmemorizer.screens.home.HomeRoute

interface Routes {
    val route: String
    @Composable
    fun Builder(entry: NavBackStackEntry, navController: NavController)
}

fun  NavController.toRoute(route: Routes) {
    this.navigate(route.route)
}

val navigation = listOf(
    HomeRoute(),
    AddWordRoute(),
    EditWordScreen(),
)
