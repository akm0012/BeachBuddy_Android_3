package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import com.andrew.beachbuddy.ui.specific.sunprogress.SunProgressRealistic
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@Composable
fun CurrentUvDashboardTile(
    weatherDM: WeatherDM?,
    usersWithScores: List<UserWithScores>,
    onUserClicked: (User) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxHeight()
            .padding(end = 8.dp)
    ) {

        val zoneId = ZoneId.of("America/New_York")
        val date = LocalDate.of(2025, 7, 23)

        fun timeToMillis(hour: Int, minute: Int): Long {
            val localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute))
            return localDateTime.atZone(zoneId).toInstant().toEpochMilli()
        }

        val sunriseMillis = timeToMillis(6, 30)
        val solarNoonMillis = timeToMillis(13, 39)
        val sunsetMillis = timeToMillis(20, 30)
        val currentTimeMillis = timeToMillis(9, 39)

        SunProgressRealistic(
            sunrise = sunriseMillis,
            solarNoon = solarNoonMillis,
            sunset = sunsetMillis,
            currentTime = currentTimeMillis,
            modifier = Modifier.weight(1f)
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