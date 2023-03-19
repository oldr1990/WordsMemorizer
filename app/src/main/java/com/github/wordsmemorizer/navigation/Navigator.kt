package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.wordsmemorizer.screens.add_word.AddWord
import com.github.wordsmemorizer.screens.home.HomeScreen

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HOME.name){
        composable(Routes.HOME.name){
            HomeScreen(navController = navController)
        }
        composable(Routes.ADD_CARD.name){
            AddWord(navController = navController)
        }
    }
}