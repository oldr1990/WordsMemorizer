package com.github.wordsmemorizer.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.navigation.toRoute
import com.github.wordsmemorizer.screens.add_simple_word.EditWordScreen
import com.github.wordsmemorizer.screens.add_word.AddWordRoute
import com.github.wordsmemorizer.screens.comosables.WMBottomAppBar
import com.github.wordsmemorizer.ui.components.WMTopAppBar
import kotlinx.coroutines.CoroutineScope

class HomeRoute : Routes {
    override val route = "home"
    @Composable
    override fun Builder(entry: NavBackStackEntry, navController: NavController) {
        HomeScreen(navController = navController)
    }
}
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState,
        modifier = Modifier,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.toRoute(EditWordScreen())},
                elevation = FloatingActionButtonDefaults.elevation(4.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    stringResource(id = R.string.add),
                    tint = Color.White,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(48.dp)
                )
            }
        },
        topBar = {
            WMTopAppBar(R.string.app_name, navController)
        },
        bottomBar = {
            WMBottomAppBar()
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {

                },
            ) {
                Text(text = "Go")
            }
        }
    }
}