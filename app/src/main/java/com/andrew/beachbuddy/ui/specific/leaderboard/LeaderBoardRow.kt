package com.andrew.beachbuddy.ui.specific.leaderboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun LeaderBoardRow(
    userWithScores: UserWithScores,
    rowHeight: Dp,
    onUserClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val leaderBoardRowUiState = LeaderBoardRowUiState(userWithScores)

    LeaderBoardRow(
        name = leaderBoardRowUiState.name,
        subtitleText = leaderBoardRowUiState.getSubtitle(),
        totalScoreText = leaderBoardRowUiState.getScore(),
        profilePhotoUrl = leaderBoardRowUiState.profilePhotoUrl,
        rowHeight = rowHeight,
        onUserClicked = onUserClicked,
        modifier = modifier
    )
}

@Composable
fun LeaderBoardRow(
    name: String,
    subtitleText: String,
    totalScoreText: String,
    profilePhotoUrl: String,
    onUserClicked: () -> Unit,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 79.dp,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(rowHeight)
            .clickable { onUserClicked() }) {

        Spacer(Modifier.width(StandardPadding))

        ProfilePhoto(
            imageUrl = profilePhotoUrl,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
                .aspectRatio(1f)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = StandardPadding)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitleText,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = totalScoreText,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(end = StandardPadding)
        )
    }
}

@DarkLightTabletPreviews
@Composable
private fun LeaderBoardRowPreview() {
    BeachBuddyTheme {
        LeaderBoardRow(
            name = "Andrew",
            subtitleText = "Best Game: KanJam (4 wins)",
            totalScoreText = "99",
            profilePhotoUrl = "",
            onUserClicked = {}
        )
    }
}