package com.andrew.beachbuddy.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.DashboardViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by dashboardViewModel.weatherDomainModel.collectAsStateWithLifecycle()

    DashboardScreen(currentWeather = uiState)
}

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    currentWeather: WeatherDM?
) {

    Text(
        text = "Flag is ${currentWeather?.beachConditions?._flagColor}"
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
                uvInfo = CurrentUvInfo()
            )
        )
    }
}