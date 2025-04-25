package com.andrew.beachbuddy.network.dtos

import com.andrew.beachbuddy.network.dtos.DailyWeatherDto

data class WeatherInfoDto(

    var lat: Double,

    var lon: Double,

    var current: CurrentWeatherDto,

    var hourly: List<HourlyWeatherDto>,

    var daily: List<DailyWeatherDto>
)