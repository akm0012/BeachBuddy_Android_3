package com.andrew.beachbuddy.ui.specific.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun LeaderBoard(
    usersWithScores: List<UserWithScores>,
    onNightModeClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {

    BeachBuddyCard(modifier = modifier) {

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = StandardPadding, vertical = 8.dp)
            ) {

                Text(
                    text = "Leaderboard",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onNightModeClicked
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_dark_mode),
                        contentDescription = "Night mode",
                    )
                }

                IconButton(
                    onClick = onSettingsClicked
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_settings_24dp),
                        contentDescription = "Settings",
                    )
                }

            }

            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(usersWithScores) { userWithScores ->
                    LeaderBoardRow(
                        userWithScores = userWithScores,
                        onUserClicked = { onUserClicked(userWithScores.user) }
                    )
                }
            }
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun LeaderBoardPreview() {
    LeaderBoard(
        usersWithScores = sampleUserWithScoresList,
        onSettingsClicked = { },
        onNightModeClicked = { },
        onUserClicked = { },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
    )
}