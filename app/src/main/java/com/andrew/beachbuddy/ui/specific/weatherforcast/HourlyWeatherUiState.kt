package com.andrew.beachbuddy.ui.specific.weatherforcast

import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.extensions.capitalizeWords
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class HourlyWeatherUiState(
    hourlyWeather: HourlyWeatherInfo
) {
    val formattedTime = Instant.ofEpochSecond(hourlyWeather.time)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault()))

    val iconUrl = "https://openweathermap.org/img/wn/${hourlyWeather.iconTemplate}@2x.png"

    val summary = hourlyWeather.mainDescription.capitalizeWords()

    val feelsLike = "${hourlyWeather.feelsLike.roundToInt()}°"

    val humidity = "${hourlyWeather.humidity}%"
}