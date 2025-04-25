package com.andrew.beachbuddy.network.dtos

import com.andrew.beachbuddy.network.dtos.DailyFeelsLikeSummaryDto
import com.andrew.beachbuddy.network.dtos.DailyTempSummaryDto
import com.andrew.beachbuddy.network.dtos.WeatherSummeryDto
import com.google.gson.annotations.SerializedName

data class DailyWeatherDto(

    var dt: Long,

    var sunrise: Long,

    var sunset: Long,

    var temp: DailyTempSummaryDto,

    @SerializedName("feels_like")
    var feelsLike: DailyFeelsLikeSummaryDto,

    var humidity: Int,

    @SerializedName("wind_speed")
    var windSpeed: Double,

    var weather: List<WeatherSummeryDto>?,

    var rain: Double,

    var uvi: Double
)