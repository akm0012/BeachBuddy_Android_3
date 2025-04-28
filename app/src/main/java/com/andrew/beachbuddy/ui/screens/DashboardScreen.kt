package com.andrew.beachbuddy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.sampleWeatherDM
import com.andrew.beachbuddy.ui.specific.beachconditions.BeachConditionComposable
import com.andrew.beachbuddy.ui.specific.currentweather.CurrentWeatherComposable
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by dashboardViewModel.weatherDomainModel.collectAsStateWithLifecycle()

    if (uiState != null) {
        DashboardScreen(currentWeather = uiState!!)
    }
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    currentWeather: WeatherDM
) {

    Column {

        CurrentWeatherComposable(
            currentWeather = currentWeather,
            modifier = Modifier
                .width(230.dp)
                .height(150.dp)
        )

        BeachConditionComposable(
            weatherDM = currentWeather
        )

    }
}

@DarkLightTabletPreviews
@Composable
private fun DashboardPreview() {
    BeachBuddyTheme {
        DashboardScreen(currentWeather = sampleWeatherDM)
    }
}