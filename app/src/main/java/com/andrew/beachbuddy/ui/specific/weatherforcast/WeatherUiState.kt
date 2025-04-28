package com.andrew.beachbuddy.ui.specific.weatherforcast

import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.extensions.capitalizeWords
import com.andrew.beachbuddy.extensions.millimetersToInches
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

interface WeatherUiState {
    val id: Int
    val topTitle: String
    val imageUrl: String
    val mainBody: String
    val topSubtitle: String
    val bottomSubtitle: String
}

class HourlyWeatherUiState(
    hourlyWeather: HourlyWeatherInfo
) : WeatherUiState {

    val formattedTime = Instant.ofEpochSecond(hourlyWeather.time)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault()))

    val iconUrl = "https://openweathermap.org/img/wn/${hourlyWeather.iconTemplate}@2x.png"

    val summary = hourlyWeather.mainDescription.capitalizeWords()

    val feelsLike = "${hourlyWeather.feelsLike.roundToInt()}°"

    val humidity = "${hourlyWeather.humidity}%"

    override val id = hourlyWeather.id
    override val topTitle: String = formattedTime
    override val imageUrl = iconUrl
    override val mainBody = feelsLike
    override val topSubtitle = humidity
    override val bottomSubtitle = summary
}

class DailyWeatherUiState(
    dailyWeather: DailyWeatherInfo
) : WeatherUiState {
    val formattedTime = Instant.ofEpochSecond(dailyWeather.time)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("EE", Locale.getDefault()))

    val iconUrl = "https://openweathermap.org/img/wn/${dailyWeather.iconTemplate}@2x.png"


    val summary = dailyWeather.mainDescription.capitalizeWords()

    val feelsLike = "${dailyWeather.feelsLikeDay.roundToInt()}°"

    val rain = "${"%.2f".format(dailyWeather.rainMilliMeters.millimetersToInches())}\""

    override val id = dailyWeather.id
    override val topTitle: String = formattedTime
    override val imageUrl = iconUrl
    override val mainBody = feelsLike
    override val topSubtitle = rain
    override val bottomSubtitle = summary
}