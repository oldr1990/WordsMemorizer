package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.wordsmemorizer.screens.add_word.AddWordScreen
import com.github.wordsmemorizer.screens.home.HomeScreen

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeRoute.getRoute()){
        composable(HomeRoute.getRoute()){
            HomeScreen(navController = navController)
        }
        composable(AddWordRoute.getRoute()){
            AddWordScreen(navController = navController)
        }
    }
}