package com.andrew.beachbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.mockUserWithScoresList
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
        onScoreDecremented = scoreManagementViewModel::onScoreDecremented
    )

}

@Composable
fun ConfigureGamesScreen(
    usersWithScores: List<UserWithScores>,
    onGameAdded: (String) -> Unit,
    onScoreIncremented: (Score) -> Unit,
    onScoreDecremented: (Score) -> Unit,
    modifier: Modifier = Modifier) {


}

@DarkLightTabletPreviews
@Composable
private fun ConfigureGamesScreenPreview() {
    BeachBuddyTheme {
        ConfigureGamesScreen(
            usersWithScores = mockUserWithScoresList,
            onGameAdded = {},
            onScoreIncremented = {},
            onScoreDecremented =  {}
        )
    }
}