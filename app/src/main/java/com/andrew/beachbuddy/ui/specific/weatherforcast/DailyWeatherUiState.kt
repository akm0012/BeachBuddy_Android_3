package com.andrew.beachbuddy.ui.specific.weatherforcast

import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.extensions.capitalizeWords
import com.andrew.beachbuddy.extensions.millimetersToInches
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class DailyWeatherUiState(
    dailyWeather: DailyWeatherInfo
) {
    val formattedTime = Instant.ofEpochSecond(dailyWeather.time)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("EE", Locale.getDefault()))

    val iconUrl = "https://openweathermap.org/img/wn/${dailyWeather.iconTemplate}@2x.png"


    val summary = dailyWeather.mainDescription.capitalizeWords()

    val feelsLike = "${dailyWeather.feelsLikeDay.roundToInt()}°"

    val rain = "${"%.2f".format(dailyWeather.rainMilliMeters.millimetersToInches())}\""
}