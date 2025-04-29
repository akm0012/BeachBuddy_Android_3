package com.andrew.beachbuddy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.extensions.toast
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.sampleDailyWeatherInfoList
import com.andrew.beachbuddy.ui.sampleHourlyWeatherInfoList
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.sampleWeatherDM
import com.andrew.beachbuddy.ui.specific.beachconditions.BeachConditionComposable
import com.andrew.beachbuddy.ui.specific.currentweather.CurrentWeatherComposable
import com.andrew.beachbuddy.ui.specific.leaderboard.LeaderBoard
import com.andrew.beachbuddy.ui.specific.weatherforcast.DailyWeatherForecastCarousel
import com.andrew.beachbuddy.ui.specific.weatherforcast.HourlyWeatherForecastCarousel
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.DashboardUiState
import com.andrew.beachbuddy.ui.viewmodels.DashboardViewModel
import timber.log.Timber

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by dashboardViewModel.dashboardUiState.collectAsStateWithLifecycle()

    DashboardScreen(dashboardUiState = uiState)
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardUiState: DashboardUiState,
) {

    Row {
        Column {
            CurrentWeatherComposable(
                currentWeather = dashboardUiState.weatherDM,
                modifier = Modifier
                    .width(230.dp)
                    .height(150.dp)
            )

            BeachConditionComposable(
                weatherDM = dashboardUiState.weatherDM,
                modifier = Modifier.width(230.dp)
            )
        }

        // todo: Remove the 400.dp, that is here so I can see the leaderboard
        Column(modifier = Modifier.width(400.dp)) {
            if (dashboardUiState.hourlyWeather != null) {
                HourlyWeatherForecastCarousel(
                    hourlyWeatherInfo = dashboardUiState.hourlyWeather,
                    modifier = Modifier.weight(1f)
                )
            }
            if (dashboardUiState.dailyWeather != null) {
                DailyWeatherForecastCarousel(
                    dailyWeatherInfo = dashboardUiState.dailyWeather,
                    modifier = Modifier.weight(1f)

                )
            }
        }

        LeaderBoard(
            usersWithScores = dashboardUiState.usersWithScores,
            onUserClicked = { user ->
                Timber.d("User clicked: $user")
            })
    }
}

@DarkLightTabletPreviews
@Composable
private fun DashboardPreview() {
    BeachBuddyTheme {
        DashboardScreen(
            dashboardUiState = DashboardUiState(
                weatherDM = sampleWeatherDM,
                dailyWeather = sampleDailyWeatherInfoList,
                hourlyWeather = sampleHourlyWeatherInfoList,
                usersWithScores = sampleUserWithScoresList,
            )
        )
    }
}