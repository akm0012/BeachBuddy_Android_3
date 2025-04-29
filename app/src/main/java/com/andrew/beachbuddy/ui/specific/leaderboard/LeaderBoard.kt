package com.andrew.beachbuddy.ui.specific.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.sampleUserWithScoresList

@Composable
fun LeaderBoard(
    usersWithScores: List<UserWithScores>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(usersWithScores) { userWithScores ->
            LeaderBoardRow(
                userWithScores = userWithScores,
                onUserClicked = { onUserClicked(userWithScores.user) }
            )
        }
    }
}

@DarkLightTabletPreviews
@Composable
private fun LeaderBoardPreview() {
    LeaderBoard(
        usersWithScores = sampleUserWithScoresList,
        onUserClicked = { },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}