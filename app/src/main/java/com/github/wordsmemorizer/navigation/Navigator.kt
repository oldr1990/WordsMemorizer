package com.github.wordsmemorizer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.wordsmemorizer.core.BaseScreen
import com.github.wordsmemorizer.screens.home.HomeScreen
import com.github.wordsmemorizer.utils.decodeBase64
import com.github.wordsmemorizer.utils.encodeBase64

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        navigation.forEach { route ->
            composable(route.route + Navigation.urlArgs, arguments = Navigation.argumentList) {
                route.Builder(
                    entry = it,
                    navController = navController,
                    arguments = it.arguments?.getString("args").orEmpty().decodeBase64()
                )
            }
        }
    }
}

fun <T> NavController.push(route: Routes, arguments: Argument<T>? = null) {
    var destination = route.route
    arguments?.let {
        destination += "?${Navigation.argKey}=${it.toJson().encodeBase64()}"
    }
    this.navigate(destination)
}


fun <T> NavController.pushReplacement(
    destination: Routes,
    replacedRoute: Routes? = null,
    arguments: Argument<T>? = null
) = this.navigate(
    route = destination.route + "?${Navigation.argKey}=${arguments?.toJson()?.encodeBase64()}",
    navOptions = NavOptions.Builder()
        .setPopUpTo(
            route = replacedRoute?.route ?: this.currentBackStackEntry?.id,
            inclusive = false,
            saveState = false
        ).build(),
    navigatorExtras = null
)

object Navigation {
    const val argKey = "args"
    val argumentList: List<NamedNavArgument> = listOf(navArgument(argKey) { defaultValue = "" })
    const val urlArgs = "?$argKey={$argKey}"
}
