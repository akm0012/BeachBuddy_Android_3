package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.sampleWeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun CurrentUvDashboardTile(
    weatherDM: WeatherDM?,
    usersWithScores: List<UserWithScores>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxHeight().padding(end = 8.dp)) {
        Box(modifier = modifier
            .weight(1f)
        )

        // todo: make this a loading state if weather data is null
        if (weatherDM != null) {
            TimeToBurnUserGrid(
                weatherDM = weatherDM,
                usersWithScores = usersWithScores,
                onUserClicked = onUserClicked,
                modifier = Modifier.weight(1.2f)
            )
        }
    }

}

@DarkLightTabletPreviews
@Composable
private fun CurrentUvDashboardTilePreview() {
    BeachBuddyTheme {
        CurrentUvDashboardTile(
            weatherDM = sampleWeatherDM,
            usersWithScores = sampleUserWithScoresList,
            onUserClicked = {},
            modifier = Modifier.width(400.dp)
        )
    }
}