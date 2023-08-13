package com.github.wordsmemorizer.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.push
import com.github.wordsmemorizer.navigation.pushReplacement
import com.github.wordsmemorizer.ui.components.WMProgressBar
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> BaseScreen(
    title: Int,
    navController: NavController,
    viewModel: BaseViewModel<T>,
    content: @Composable (padding: PaddingValues) -> Unit
) {

    val progress = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.screenEvent.collect {
            keyboard?.hide()
            when (it) {
                is ScreenAction.Navigate -> {
                    progress.value = false
                    when (it.action) {
                        is NavigationAction.Push<*> -> {
                            navController.push(it.action.route, it.action.arguments)
                        }

                        NavigationAction.PopUp -> navController.popBackStack()
                        is NavigationAction.PushReplacement<*> -> {
                            navController.pushReplacement(
                                it.action.destination,
                                it.action.replacedRoute,
                                it.action.arguments
                            )
                        }
                        is NavigationAction.popUpWithResult<*> -> {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                it.action.key,
                                it.action.arguments.toJson()
                            )
                            navController.popBackStack()
                        }
                    }
                }

                is ScreenAction.Snackbar -> {
                    progress.value = false
                    when (it.message) {
                        is SnackbarMessage.FromResource -> {
                            snackbarHostState.showSnackbar(context.getString(it.message.resource))
                        }

                        is SnackbarMessage.FromString -> {
                            snackbarHostState.showSnackbar(it.message.message)
                        }
                    }
                }

                is ScreenAction.Progressbar -> {
                    progress.value = it.isLoading
                }
            }
        }
    }

    Scaffold(
        topBar = { WMTopAppBar(title = title, navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            content(it)
        }
    }
    if (progress.value) WMProgressBar()
}