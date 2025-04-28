package com.andrew.beachbuddy.ui.specific.currentweather

import androidx.annotation.ColorRes
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.enums.FlagColor
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.util.DEFAULT_LOCALITY
import kotlin.math.roundToInt


class CurrentWeatherUiState(
    weather: WeatherDM?,
) {

    private val currentWeather = weather?.currentWeather
    private val beachConditions = weather?.beachConditions
    private val locality = weather?.locality

    fun getCityName(): String {

        // Special case, if Beach is Closed
        if (beachConditions?.flagColor == FlagColor.DOUBLE_RED) {
            return "BEACH CLOSED!"
        }

        return locality ?: DEFAULT_LOCALITY
    }

    fun getWeatherDescription(): String {
        return currentWeather?.mainDescription ?: "N/A"
    }

    fun getIconUrl(): String {
        return if (currentWeather == null) {
            ""
        } else {
            "https://openweathermap.org/img/wn/${currentWeather.iconTemplate}@2x.png"
        }
    }

    fun getFeelsLikeTemp(): String {
        return if (currentWeather == null) {
            "N/A"
        } else {
            "${currentWeather.feelsLikeTemp.roundToInt()}°"
        }
    }

    @ColorRes
    fun getCardBackgroundColor(): Int {

        return when (beachConditions?.flagColor) {
            FlagColor.GREEN, null -> R.color.flag_green
            FlagColor.YELLOW -> R.color.flag_yellow
            FlagColor.PURPLE -> R.color.flag_purple
            FlagColor.RED, FlagColor.DOUBLE_RED -> R.color.flag_red
            FlagColor.UNKNOWN -> R.color.white
        }
    }

    @ColorRes
    fun getTextColor(): Int {
        return when (beachConditions?.flagColor) {
            FlagColor.GREEN, null -> R.color.white
            FlagColor.YELLOW -> R.color.dark_gray
            FlagColor.PURPLE -> R.color.white
            FlagColor.RED, FlagColor.DOUBLE_RED -> R.color.white
            FlagColor.UNKNOWN -> R.color.dashboard_text_dark
        }
    }

    @ColorRes
    fun getSecondaryTextColor(): Int {
        return when (beachConditions?.flagColor) {
            FlagColor.GREEN, null -> R.color.white
            FlagColor.YELLOW -> R.color.dark_gray
            FlagColor.PURPLE -> R.color.white
            FlagColor.RED, FlagColor.DOUBLE_RED -> R.color.white
            FlagColor.UNKNOWN -> R.color.dashboard_text
        }
    }

}