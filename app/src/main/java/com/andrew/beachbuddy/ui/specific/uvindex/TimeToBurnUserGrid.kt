package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.sampleWeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun TimeToBurnUserGrid(
    weatherDM: WeatherDM,
    usersWithScores: List<UserWithScores>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxHeight()
    ) {

        Text(
            text = stringResource(R.string.time_to_burn),
            color = colorResource(R.color.dashboard_text_dark),
            style = MaterialTheme.typography.titleSmall,
        )

        LazyVerticalGrid(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(5.dp),
            columns = GridCells.Fixed(3),
        ) {
            items(items = usersWithScores.map { it.user }.sortedBy { it.firstName }, key = { it.userId }) { user ->
                TimeToBurnProfileTile(
                    weatherDM = weatherDM,
                    user = user,
                    onTileClicked = { onUserClicked(user) }
                )
            }
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun TimeToBurnUserGridPreview() {
    BeachBuddyTheme {
        TimeToBurnUserGrid(
            weatherDM = sampleWeatherDM,
            usersWithScores = sampleUserWithScoresList,
            onUserClicked = {}
        )
    }
}