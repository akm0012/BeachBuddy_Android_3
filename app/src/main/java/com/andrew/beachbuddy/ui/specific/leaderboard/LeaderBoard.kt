package com.andrew.beachbuddy.ui.specific.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
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

            var parentHeight by remember { mutableIntStateOf(0) }
            val density = LocalDensity.current // ✅ capture density here

            // Precompute row height only when parentHeight and users change
            val rowHeight = remember(parentHeight, usersWithScores, density) {
                if (parentHeight == 0 || usersWithScores.isEmpty()) return@remember 0.dp

                val itemCount = usersWithScores.size
                val spacing = with(density) { 8.dp.roundToPx() * (itemCount - 1) }
                val padding = with(density) { 8.dp.roundToPx() * 2 }
                val adjustedHeight = parentHeight - spacing - padding
                with(density) { (adjustedHeight / itemCount).toDp() }
            }

            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned {
                        parentHeight = it.size.height
                    }
            ) {
                items(usersWithScores) { userWithScores ->

                    LeaderBoardRow(
                        userWithScores = userWithScores,
                        onUserClicked = { onUserClicked(userWithScores.user) },
                        rowHeight = rowHeight
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