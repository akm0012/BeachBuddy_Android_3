package com.andrew.beachbuddy.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.beachbuddy.repository.DashboardRepository
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    val weatherDomainModel: StateFlow<WeatherDM?> =
        dashboardRepository.weatherDomainModelFlow
            .onStart {
                // todo: move this to a better spot as this updated all the things, not just this
                dashboardRepository.refreshDashboard()
            }
            .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

}