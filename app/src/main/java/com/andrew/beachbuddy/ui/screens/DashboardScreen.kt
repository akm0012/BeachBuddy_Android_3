package com.andrew.beachbuddy.ui.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.specific.currentweather.CurrentWeatherComposable
import com.andrew.beachbuddy.ui.specific.currentweather.CurrentWeatherUiState
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

    val currentWeatherUiState = CurrentWeatherUiState(currentWeather)

    CurrentWeatherComposable(
        cityName = currentWeatherUiState.getCityName(),
        description = currentWeatherUiState.getWeatherDescription(),
        iconUrl = currentWeatherUiState.getIconUrl(),
        feelsLikeTemp = currentWeatherUiState.getFeelsLikeTemp(),
        backgroundColor = currentWeatherUiState.getCardBackgroundColor(),
        textColor = currentWeatherUiState.getTextColor(),
        secondaryTextColor = currentWeatherUiState.getSecondaryTextColor(),
        modifier = Modifier.width(230.dp).height(150.dp)
    )
}

@DarkLightPhonePreviews
@Composable
private fun DashboardPreview() {
    BeachBuddyTheme {
        DashboardScreen(
            currentWeather = WeatherDM(
                currentWeather = CurrentWeather(),
                beachConditions = BeachConditions(
                    _flagColor = "GREEN"
                ),
                uvInfo = CurrentUvInfo(),
                locality = "Siesta Key"
            )
        )
    }
}