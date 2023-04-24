package com.github.wordsmemorizer.screens

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.toRoute
import com.github.wordsmemorizer.screens.ScreenEvent.*
import com.github.wordsmemorizer.screens.SnackbarMessage.*
import com.github.wordsmemorizer.ui.components.WMProgressBar

@Composable
fun <T> BaseScreen(
    navController: NavController,
    viewModel: BaseViewModel<T>,
    scaffoldState: ScaffoldState,
    content: @Composable () -> Unit
) {
    val progress = remember { mutableStateOf(false) }
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.screenEvent.collect {
            when (it) {
                is Navigate -> {
                    when (it.action) {
                        is NavigationAction.GoTo -> {
                            navController.toRoute(it.action.route, it.action.arguments)
                        }
                        NavigationAction.PopUp -> navController.popBackStack()
                    }
                }
                is Snackbar -> {
                    when (it.message) {
                        is FromResource -> {
                            scaffoldState.snackbarHostState.showSnackbar(context.getString(it.message.resource))
                        }
                        is FromString -> {
                            scaffoldState.snackbarHostState.showSnackbar(it.message.message)
                        }
                    }
                }
                is Progressbar -> {
                    progress.value = it.isLoading
                }
            }
        }
    }
    content()
    if (progress.value) WMProgressBar()

}