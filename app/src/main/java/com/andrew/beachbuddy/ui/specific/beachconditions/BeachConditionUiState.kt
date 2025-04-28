package com.andrew.beachbuddy.ui.specific.beachconditions

import android.text.TextUtils
import androidx.annotation.DrawableRes
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.enums.BeachConditionItemType
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.enums.BeachConditionItemType.*
import com.andrew.beachbuddy.extensions.capitalizeWords
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


class BeachConditionUiState(
    private val itemType: BeachConditionItemType,
    weatherDM: WeatherDM,
) {

    private val weatherInfo = weatherDM.currentWeather
    private val beachConditions = weatherDM.beachConditions

    @DrawableRes
    val iconDrawable = when (itemType) {
        CLOUD_COVERAGE_PERCENT -> R.drawable.ic_clouds_100
        WIND -> R.drawable.ic_air_100
        RESPIRATORY_IRRITATION -> R.drawable.ic_particle_100
        SURF -> R.drawable.ic_surfing_100
        JELLY_FISH -> R.drawable.ic_jellyfish_100
        TIME_UPDATED -> R.drawable.ic_delivery_time_100
    }

    val title = when (itemType) {
        CLOUD_COVERAGE_PERCENT -> "Cloud Coverage"
        WIND -> "Wind"
        RESPIRATORY_IRRITATION -> "Respiratory Irritation"
        SURF -> "Surf"
        JELLY_FISH -> "Jelly Fish"
        TIME_UPDATED -> "Updated At"
    }

    val content = when (itemType) {

        CLOUD_COVERAGE_PERCENT -> {
            "${weatherInfo.cloudPercent}%"
        }

        WIND -> {
            "${weatherInfo.windSpeed.toInt()} mph ${weatherInfo.windDeg}°"
        }

        RESPIRATORY_IRRITATION -> beachConditions.respiratoryIrritation.capitalizeWords()
            ?: "N/A"

        SURF -> {

            val surfOverview = beachConditions.surfCondition.capitalizeWords()
                ?: "N/A"

            var surfHeight = beachConditions.surfHeight.capitalizeWords()
                ?: ""

            if (!TextUtils.isEmpty(surfHeight)) {
                surfHeight = "($surfHeight)"
            }

            "$surfOverview $surfHeight".trim()
        }

        JELLY_FISH -> beachConditions.jellyFish.capitalizeWords()
            ?: "N/A"

        TIME_UPDATED -> {
            beachConditions.timeUpdated.toFormattedTime()
        }
    }

    private fun Long.toFormattedTime(): String =
        Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("EE h:mm a", Locale.getDefault()))

}