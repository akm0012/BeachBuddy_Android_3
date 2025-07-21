package com.andrew.beachbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.database.model.SunsetInfo
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.database.model.maxScore
import com.andrew.beachbuddy.repository.DashboardRepository
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


data class DashboardUiState(
    val weatherDM: WeatherDM? = null,
    val hourlyWeather: List<HourlyWeatherInfo>? = null,
    val dailyWeather: List<DailyWeatherInfo>? = null,
    val usersWithScores: List<UserWithScores> = emptyList(),
    val sunSetInfo: SunsetInfo? = null
)


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    private val _dashboardUiState = MutableStateFlow(DashboardUiState())
    val dashboardUiState = _dashboardUiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                dashboardRepository.weatherDomainModelFlow,
                dashboardRepository.hourlyWeatherFlow,
                dashboardRepository.dailyWeatherFlow,
                dashboardRepository.userWithScoresFlow,
                dashboardRepository.sunsetInfoFlow,
            ) { weatherDM, hourlyWeather, dailyWeather, usersWithScores, sunsetInfo ->

                // Sort the users with scores by score, then name.
                val sortedUsersWithScores = usersWithScores.sortedWith(
                    compareByDescending<UserWithScores> { it.maxScore() }
                        .thenBy { it.user.firstName.lowercase() }
                )

                // Create a new DashboardUiState with the latest data
                DashboardUiState(
                    weatherDM = weatherDM,
                    hourlyWeather = hourlyWeather,
                    dailyWeather = dailyWeather,
                    usersWithScores = sortedUsersWithScores,
                    sunSetInfo = sunsetInfo
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = _dashboardUiState.value
            ).collect { newState ->
                _dashboardUiState.value = newState
            }
        }

        viewModelScope.launch {
            dashboardRepository.refreshDashboard()
        }
    }
}