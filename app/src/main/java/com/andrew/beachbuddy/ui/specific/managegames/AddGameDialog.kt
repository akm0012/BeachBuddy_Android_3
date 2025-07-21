package com.andrew.beachbuddy.ui.specific.managegames

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun AddGameDialog(
    onGameNameEntered: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismissRequest) {

        Card {
            var gameName by rememberSaveable { mutableStateOf("") }

            val trimmedInput = gameName.trim()

            Column(modifier = modifier.padding(16.dp)) {

                TextField(
                    value = gameName,
                    onValueChange = { gameName = it },
                    singleLine = true,
                    label = { Text(text = "Enter the new game name") },
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(top = StandardPadding)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Button(onDismissRequest) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            onGameNameEntered(trimmedInput)
                        },
                        enabled = trimmedInput.isNotEmpty()
                    ) { Text("Add Game") }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AddGameDialogPreview() {
    BeachBuddyTheme {
        AddGameDialog(onGameNameEntered = { }, onDismissRequest = { })
    }
}