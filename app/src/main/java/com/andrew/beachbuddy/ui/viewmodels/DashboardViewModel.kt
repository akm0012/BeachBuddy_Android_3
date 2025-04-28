package com.andrew.beachbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
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
)


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    @Deprecated("This is the old way I was doing this. Now it all happens in the init")
    val weatherDomainModel: StateFlow<WeatherDM?> =
        dashboardRepository.weatherDomainModelFlow
            .onStart {
                // todo: move this to a better spot as this updated all the things, not just this
//                dashboardRepository.refreshDashboard()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )


    private val _dashboardUiState = MutableStateFlow(DashboardUiState())
    val dashboardUiState = _dashboardUiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                dashboardRepository.weatherDomainModelFlow,
                dashboardRepository.hourlyWeatherFlow,
                dashboardRepository.dailyWeatherFlow
            ) { weatherDM, hourlyWeather, dailyWeather ->
                // Create a new DashboardUiState with the latest data
                DashboardUiState(
                    weatherDM = weatherDM,
                    hourlyWeather = hourlyWeather,
                    dailyWeather = dailyWeather,
                )
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = _dashboardUiState.value
                )
                .collect { newState ->
                    _dashboardUiState.value = newState
                }
        }

        viewModelScope.launch {
            dashboardRepository.refreshDashboard()
        }
    }
}