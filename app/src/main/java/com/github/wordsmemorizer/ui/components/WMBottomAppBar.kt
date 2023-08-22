package com.github.wordsmemorizer.ui.components


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.navigation.Routes
import com.github.wordsmemorizer.screens.editor.EditorScreen
import com.github.wordsmemorizer.screens.home.HomeScreen

@Composable
fun WMBottomAppBar(selected: BottomNavData, onSelect: (BottomNavData) -> Unit) {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp),
     /*   floatingActionButton = {
        FloatingActionButton(onClick = { *//*TODO*//* }) {
            Icon(Icons.Default.Add, null, modifier = Modifier.size(24.dp))
        }
    },*/ actions = {
        BottomNavData.values().forEach {
            NavigationBarItem( selected = it == selected, onClick = { onSelect(it) }, icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(it.icon, null, modifier = Modifier.size(24.dp))
                    Text(stringResource(id = it.label), style = MaterialTheme.typography.bodyLarge)
                }
            })
        }
    })
}

enum class BottomNavData(val route: Routes, val icon: ImageVector, val label: Int) {
    List(EditorScreen, Icons.Default.List, R.string.list),
    Home(HomeScreen, Icons.Default.Home, R.string.home),
    History(HomeScreen, Icons.Default.Refresh, R.string.history),
}
