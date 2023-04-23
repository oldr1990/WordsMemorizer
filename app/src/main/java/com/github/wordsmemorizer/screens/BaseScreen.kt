package com.github.wordsmemorizer.screens

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.toRoute
import com.github.wordsmemorizer.ui.components.WMProgressBar

@Composable
fun <T> BaseScreen(
    navController: NavController,
    viewModel: BaseViewModel<T>,
    scaffoldState: ScaffoldState,
    content: @Composable () -> Unit
) {
    val progress = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModel.screenEvent.collect {
            when (it) {
                is ScreenEvent.Navigate -> {
                    when (it.action) {
                        is NavigationAction.GoTo -> {
                            navController.toRoute(it.action.route, it.action.arguments)
                        }
                        NavigationAction.PopUp -> navController.popBackStack()
                    }
                }
                is ScreenEvent.Snackbar -> { scaffoldState.snackbarHostState.showSnackbar(it.message) }
                is ScreenEvent.Progressbar -> {
                    progress.value = it.isLoading
                }
            }
        }
    }
    content()
    if (progress.value) WMProgressBar()

}