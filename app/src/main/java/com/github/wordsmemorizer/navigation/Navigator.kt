package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.wordsmemorizer.screens.home.HomeRoute

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeRoute().route){
        navigation.forEach { route ->
            composable(route.route){
                route.Builder(entry = it, navController = navController)
            }
        }
    }
}