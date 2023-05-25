package com.github.wordsmemorizer.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WMTopAppBar(title: Int, navController: NavController) {
    TopAppBar(elevation = 4.dp) {
        if (navController.previousBackStackEntry != null) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            color = Color.White,
            text = stringResource(id = title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}