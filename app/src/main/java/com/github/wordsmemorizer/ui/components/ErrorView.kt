package com.github.wordsmemorizer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.core.WMError

@Composable
fun ErrorView(message: String, modifier: Modifier = Modifier, repeat: () -> Unit) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Rounded.Warning,
            "",
            modifier = Modifier.size(150.dp),
            tint = Color.Red.copy(0.6f)
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(text = message, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Button(onClick = repeat) {
            Text(stringResource(id = R.string.try_again))
        }
    }
}

@Composable
fun ErrorView(error: WMError, modifier: Modifier = Modifier, repeat: () -> Unit) {
    val string = when (error) {
        is WMError.ErrorString -> {
            error.message
        }

        is WMError.ErrorResource -> {
            stringResource(id = error.resId)
        }
    }
    ErrorView(string, modifier, repeat)
}

@Composable
@Preview
private fun ErrorViewPreview() {
    Preview {
        ErrorView(stringResource(id = R.string.error_no_internet_connection)) {
        }
    }
}