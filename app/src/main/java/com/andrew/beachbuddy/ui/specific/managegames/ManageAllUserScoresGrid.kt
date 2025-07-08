package com.andrew.beachbuddy.ui.specific.managegames

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun ManageAllUserScoresGrid(
    userWithScores: List<UserWithScores>,
    onIncrementClicked: (Score) -> Unit,
    onDecrementClicked: (Score) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(3),
        modifier = modifier
    ) {
        items(items = userWithScores, key = { it.user.userId }) {
            EditUserAndScoreTile(
                userWithScores = it,
                onIncrementClicked = onIncrementClicked,
                onDecrementClicked = onDecrementClicked
            )
        }
    }
}

@DarkLightTabletPreviews
@Composable
private fun ManageAllUserScoresGridPreview() {
    BeachBuddyTheme {
        ManageAllUserScoresGrid(
            userWithScores = sampleUserWithScoresList,
            onDecrementClicked = { },
            onIncrementClicked = { }
        )
    }

}