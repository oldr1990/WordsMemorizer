package com.github.wordsmemorizer.navigation

import androidx.navigation.NavController

interface Routes<T> {
    val route: String
    fun getRoute(arguments: T? = null): String =
        if (arguments == null) {
            route
        } else {
            converter(arguments)
        }

    fun converter(arguments: T?): String = route
}


fun <T> NavController.toRoute(route: Routes<T>, arguments: T? = null) {
    this.navigate(route.getRoute(arguments))
}

object HomeRoute : Routes<String> {
    override val route = "home"
}

object AddWordRoute : Routes<String> {
    override val route = "add_card/"
    override fun converter(arguments: String?): String = route + arguments.orEmpty()
}