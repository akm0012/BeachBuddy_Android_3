package com.andrew.beachbuddy.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.enums.FlagColor
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.sampleSunsetInfo
import com.andrew.beachbuddy.ui.sampleDailyWeatherInfoList
import com.andrew.beachbuddy.ui.sampleHourlyWeatherInfoList
import com.andrew.beachbuddy.ui.sampleUserWithScoresList
import com.andrew.beachbuddy.ui.sampleWeatherDM
import com.andrew.beachbuddy.ui.specific.beachconditions.BeachConditionComposable
import com.andrew.beachbuddy.ui.specific.currentweather.CurrentWeatherComposable
import com.andrew.beachbuddy.ui.specific.leaderboard.LeaderBoard
import com.andrew.beachbuddy.ui.specific.sunset.SunsetTimerComposable
import com.andrew.beachbuddy.ui.specific.uvindex.CurrentUvDashboardTile
import com.andrew.beachbuddy.ui.specific.weatherforcast.DailyWeatherForecastCarousel
import com.andrew.beachbuddy.ui.specific.weatherforcast.HourlyWeatherForecastCarousel
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.DashboardUiState
import com.andrew.beachbuddy.ui.viewmodels.DashboardViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun DashboardScreen(
    onNightModeClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {

    val uiState by dashboardViewModel.dashboardUiState.collectAsStateWithLifecycle()

    DashboardScreen(
        dashboardUiState = uiState,
        onNightModeClicked = onNightModeClicked,
        onSettingsClicked = onSettingsClicked,
        modifier = modifier
    )
}

@Composable
fun DashboardScreen(
    dashboardUiState: DashboardUiState,
    onNightModeClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val backgroundColor = when (dashboardUiState.weatherDM?.beachConditions?.flagColor) {
        FlagColor.DOUBLE_RED -> colorResource(R.color.flag_red)
        else -> colorResource(R.color.dashboard_background_color)
    }

    Box(modifier = modifier.background(backgroundColor)) {

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

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row {
                    SunsetTimerComposable(
                        sunsetInfo = dashboardUiState.sunSetInfo,
                        modifier = Modifier
                            .height(150.dp)
                    )

                    BeachBuddyCard(
                        modifier = Modifier
                            .height(150.dp)
                            .weight(1f)
                    ) {

                        val scope = rememberCoroutineScope()
                        val context = LocalContext.current

                        CurrentUvDashboardTile(
                            weatherDM = dashboardUiState.weatherDM,
                            usersWithScores = dashboardUiState.usersWithScores,
                            onUserClicked = {
                                scope.launch {
                                    Toast.makeText(context, "Bam: ${it.firstName}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }

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
                onNightModeClicked = onNightModeClicked,
                onSettingsClicked = onSettingsClicked,
                onUserClicked = { user ->
                    Timber.d("User clicked: $user")
                }, modifier = Modifier
                    .width(270.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@DarkLightTabletPreviews
@Composable
private fun DashboardPreview() {
    BeachBuddyTheme {
        DashboardScreen(
            onSettingsClicked = { },
            onNightModeClicked = { },
            dashboardUiState = DashboardUiState(
                weatherDM = sampleWeatherDM,
                dailyWeather = sampleDailyWeatherInfoList,
                hourlyWeather = sampleHourlyWeatherInfoList,
                usersWithScores = sampleUserWithScoresList,
                sunSetInfo = null
            )
        )
    }
}