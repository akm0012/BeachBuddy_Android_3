package com.andrew.beachbuddy.ui.domainmodels

import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather


data class WeatherDM(
    val currentWeather: CurrentWeather,
    val beachConditions: BeachConditions,
    val uvInfo: CurrentUvInfo,
)
