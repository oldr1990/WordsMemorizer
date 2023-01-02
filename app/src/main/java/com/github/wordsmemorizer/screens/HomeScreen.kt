package com.github.wordsmemorizer.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.ui.components.WMTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier,
    topBar = {
        WMTopAppBar("Burning Words", navController, false)
    }) { 
        Column(modifier = Modifier) {
            Button(onClick = {navController.navigate(Routes.addCard) }) {
                Text(text = "Go")
            }
        }
    }
}