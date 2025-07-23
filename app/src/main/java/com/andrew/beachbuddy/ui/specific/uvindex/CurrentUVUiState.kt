package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.annotation.ColorRes
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import timber.log.Timber
import kotlin.math.roundToInt

const val CAN_NOT_BURN_MESSAGE = "10h+"

class CurrentUVUiState(
    weather: WeatherDM
) {
    private val currentWeather = weather.currentWeather
    private val currentUvInfo = weather.uvInfo

//    fun getSunViewStartTime(): String {
//        return DateTime(currentWeather.sunrise * 1000).withZone(DateTimeZone.getDefault()).toString("H:mm")
//    }
//
//    fun getSunViewEndTime(): String {
//        return DateTime(currentWeather.sunset * 1000).withZone(DateTimeZone.getDefault()).toString("H:mm")
//    }
//
//    fun getSunViewCurrentTime(): String {
//        return DateTime().withZone(DateTimeZone.getDefault()).toString("H:mm")
//    }

    @ColorRes
    fun getUvColorForTimeToBurn(skinType: Int?): Int {

        try {
            return when (getMinutesToBurn(skinType)) {
                in 0 until  21 -> R.color.uv_extreme
                in 21 until 30 -> R.color.uv_very_high
                in 31 until 60 -> R.color.uv_high
                in 61 until 120 -> R.color.uv_moderate
                in 121 until 5 * 60 -> R.color.uv_low
                else -> R.color.uv_index_view_background
            }
        } catch (e: NumberFormatException) {
            Timber.w(e, "Can not parse UV Index...")
        }

        return R.color.card_background_color
    }

    @ColorRes
    fun getUvColor(): Int {

        try {
            return when (getUvIndex().toDouble()) {
                in 0.0..2.999 -> R.color.uv_low
                in 3.0..5.999 -> R.color.uv_moderate
                in 6.0..7.999 -> R.color.uv_high
                in 8.0..10.999 -> R.color.uv_very_high
                else -> R.color.uv_extreme
            }
        } catch (e: NumberFormatException) {
            Timber.w(e, "Can not parse UV Index...")
        }

        return R.color.colorAccent
    }

    fun getUvIndex(): String {
        val currentUv = currentUvInfo.currentUv ?: return "N/A"

        val cloudMultiplier = when (currentWeather.cloudPercent) {
            in 0..20 -> 1.0
            in 21..70 -> 0.89
            in 70..90 -> 0.73
            in 91..100 -> 0.31
            else -> 1.0
        }

        val adjustedUvIndex = currentUv * cloudMultiplier

        val roundedUvIndex = String.format("%.2f", adjustedUvIndex).toDouble()

        return roundedUvIndex.toString()
    }

    /**
     * Gets the time to burn in this format:
     * 1h 35m
     * 35m
     *
     * Returns null if UV is 0, so you can't burn.
     * Returns "N/A" if some error occurred.
     */
    fun getTimeToBurn(skinType: Int?): String? {

        try {
            val uvIndex = getUvIndex().toDouble()

            if (uvIndex == 0.0) {
//                return "∞"
                return null
            }

            val minToBurn = getMinutesToBurn(skinType)
            return formatMinutes(minToBurn)

        } catch (e: NumberFormatException) {
            Timber.w(e, "Can not parse UV Index...")
        }

        return "N/A"
    }

    private fun getMinutesToBurn(skinType: Int?): Int {
        val uvIndex = getUvIndex().toDouble()

        val skinTypeMultiplier = when (skinType) {
            1 -> 2.5
            2 -> 3.0
            3 -> 4.0
            4 -> 5.0
            5 -> 8.0
            6 -> 15.0
            else -> 2.5
        }

        val minToBurn = ((200 * skinTypeMultiplier) / (3 * uvIndex)).roundToInt()

        return minToBurn
    }

    /**
     * Converts a duration in minutes to a human-readable string format.
     *
     * Examples:
     * - 59 -> "59m"
     * - 60 -> "1h+"
     * - 95 -> "2h+"
     * - 400 -> "6h+"
     *
     * @param minutes The total number of minutes.
     * @return A formatted string representing the time in hours and minutes.
     */
    private fun formatMinutes(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        if (hours >= 10 && remainingMinutes >= 0) {
            return CAN_NOT_BURN_MESSAGE
        }

        return when {
            hours > 0 -> "${hours}h+"
            else -> "${remainingMinutes}m"
        }
    }
}