package com.andrew.beachbuddy.ui.specific.managegames

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.inject.BASE_ENDPOINT
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.mockUserWithScoresList
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun EditUserAndScoreTile(
    userWithScores: UserWithScores,
    onIncrementClicked: (Score) -> Unit,
    onDecrementClicked: (Score) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            ScoreUserHeader(
                userFirstName = userWithScores.user.firstName,
                userProfilePhotoUrl = userWithScores.user.photoUrl,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            userWithScores.scores.forEach { score ->
                EditScoreRow(
                    gameName = score.name,
                    score = score.winCount,
                    onIncrementClicked = { onIncrementClicked(score) },
                    onDecrementClicked = { onDecrementClicked(score) }
                )
            }
        }
    }

}

@DarkLightPhonePreviews
@Composable
private fun EditUserAndScoreTilePreview() {
    BeachBuddyTheme {

        EditUserAndScoreTile(
            userWithScores = mockUserWithScoresList.first(),
            onDecrementClicked = {},
            onIncrementClicked = {}
        )

    }
}