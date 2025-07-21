package com.andrew.beachbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.mockUserWithScoresList
import com.andrew.beachbuddy.ui.specific.managegames.AddGameDialog
import com.andrew.beachbuddy.ui.specific.managegames.ManageAllUserScoresGrid
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.ScoreManagementViewModel

@Composable
fun ConfigureGamesScreen(
    scoreManagementViewModel: ScoreManagementViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val usersWithScores by scoreManagementViewModel.usersWithScores.collectAsStateWithLifecycle()

    ConfigureGamesScreen(
        usersWithScores = usersWithScores,
        onGameAdded = scoreManagementViewModel::onAddNewGame,
        onScoreIncremented = scoreManagementViewModel::onScoreIncremented,
        onScoreDecremented = scoreManagementViewModel::onScoreDecremented,
        modifier = modifier
    )

}

@Composable
fun ConfigureGamesScreen(
    usersWithScores: List<UserWithScores>,
    onGameAdded: (String) -> Unit,
    onScoreIncremented: (Score) -> Unit,
    onScoreDecremented: (Score) -> Unit,
    modifier: Modifier = Modifier
) {

    var showAddGameDialog by rememberSaveable { mutableStateOf(false) }

    if (showAddGameDialog) {
        AddGameDialog(
            onGameNameEntered = { gameName ->
                onGameAdded(gameName)
                showAddGameDialog = false
            },
            onDismissRequest = {
                showAddGameDialog = false
            }
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        Button(
            onClick = { showAddGameDialog = true },
            modifier = Modifier.padding(bottom = 8.dp)
        ) { Text(text = "Add Game") }

        ManageAllUserScoresGrid(
            userWithScores = usersWithScores,
            onIncrementClicked = onScoreIncremented,
            onDecrementClicked = onScoreDecremented
        )
    }
}

@DarkLightTabletPreviews
@Composable
private fun ConfigureGamesScreenPreview() {
    BeachBuddyTheme {
        ConfigureGamesScreen(
            usersWithScores = mockUserWithScoresList,
            onGameAdded = {},
            onScoreIncremented = {},
            onScoreDecremented = {}
        )
    }
}