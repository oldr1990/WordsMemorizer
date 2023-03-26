package com.github.wordsmemorizer.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.wordsmemorizer.R
import com.github.wordsmemorizer.models.LexicalCategories
import com.github.wordsmemorizer.screens.add_word.AddWordState
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddWordView(
    modifier: Modifier = Modifier,
    word: AddWordState,
    onValueChanged: (AddWordState) -> Unit,
) {
    var definitionText by remember { mutableStateOf("") }
    Card(elevation = 4.dp, modifier = modifier.padding(bottom = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WMTextField(
                text = word.name,
                hint = stringResource(id = R.string.input_word),
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(word.copy(name = it))
                })
            WMTextField(
                text = word.phonetic,
                keyboardAction = ImeAction.Next,
                hint = stringResource(id = R.string.phonetic),
                onValueChange = {
                    onValueChanged(word.copy(phonetic = it))
                })
            WMTextField(
                text = word.sound,
                hint = stringResource(id = R.string.link_to_sound),
                keyboardAction = ImeAction.Next,
                onValueChange = {
                    onValueChanged(word.copy(sound = it))
                })
            Text(
                text = stringResource(id = R.string.lexical_catogories),
                style = MaterialTheme.typography.h5
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                mainAxisSpacing = 8.dp,
                crossAxisSpacing = (-8).dp,
                mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
                lastLineMainAxisAlignment = FlowMainAxisAlignment.Start
            ) {
                LexicalCategories.values().forEach {
                    FilterChip(
                        onClick = {
                            onValueChanged(
                                word.copy(
                                    lexicalTypes = if (word.lexicalTypes.contains(it)) {
                                        word.lexicalTypes - it
                                    } else {
                                        word.lexicalTypes + it
                                    }
                                )
                            )
                        },
                        selected = word.lexicalTypes.contains(it),
                        colors = ChipDefaults.filterChipColors(
                            selectedBackgroundColor = MaterialTheme.colors.primary,
                            selectedContentColor = MaterialTheme.colors.background,
                        )
                    ) {
                        Text(text = it.name)
                    }
                }
            }
            Divider()
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.definitions),
                style = MaterialTheme.typography.h6
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                if (word.definitions.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.empty_definitions),
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.h5
                    )
                } else {
                    word.definitions.forEach {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.weight(weight = 1f, fill = true),
                                text = it,
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.primaryVariant,
                            )
                            IconButton(
                                modifier = Modifier.padding(0.dp),
                                onClick = { onValueChanged(word.copy(definitions = word.definitions - it)) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = stringResource(id = R.string.delete)
                                )
                            }
                        }
                    }
                }
                WMSpacer()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WMTextField(
                        modifier = modifier
                            .weight(weight = 1f, fill = true)
                            .padding(end = 16.dp),
                        text = definitionText,
                        hint = stringResource(id = R.string.inptu_definition),
                        onValueChange = { definitionText = it })
                    Button(
                        modifier = Modifier.padding(bottom = 12.dp),
                        onClick = {
                            if (definitionText.isNotEmpty()
                                && !word.definitions.contains(definitionText)
                            ) {
                                onValueChanged(word.copy(definitions = word.definitions + definitionText))
                                definitionText = ""
                            }
                        }) {
                        Text(text = stringResource(id = R.string.add), maxLines = 1)
                    }
                }
            }
        }
    }
}