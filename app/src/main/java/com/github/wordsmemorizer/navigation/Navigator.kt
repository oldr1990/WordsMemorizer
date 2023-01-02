package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.wordsmemorizer.screens.HomeScreen

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.home){
        composable(Routes.home){
            HomeScreen(navController = navController)
        }
        composable(Routes.addCard){
            HomeScreen(navController = navController)
        }
    }
}