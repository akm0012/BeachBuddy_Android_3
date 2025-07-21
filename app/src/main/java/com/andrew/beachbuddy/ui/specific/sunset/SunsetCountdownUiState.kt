package com.andrew.beachbuddy.ui.specific.sunset

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class SunsetCountdownUiState(
    private val currentTime: Long,
    private val sunrise: Long,
    private val sunset: Long,
    private val sunriseNextDay: Long,
    private val sunsetPrevDay: Long,
) {

    var hasErrorBeenReportedOnce = false

    fun getBottomLabel(): String {
        return if (currentTime > sunset) {
            "SUNRISE"
        } else {
            "SUNSET"
        }
    }

    fun getTimerText(): String {
        val zoneId = ZoneId.systemDefault()
        val currentDateTime = Instant.ofEpochMilli(currentTime).atZone(zoneId)

        return if (currentTime > sunset || currentTime < sunrise) {
            // Time until next sunrise
            // Fixme: This is technically wrong as we are not using the sunrise time from the next day, but it's close enough
            val sunriseDateTime = Instant.ofEpochMilli(sunriseNextDay).atZone(zoneId)
            val duration = Duration.between(currentDateTime, sunriseDateTime)
            val minutes = duration.toMinutes() % 60
            "${duration.toHours()}h ${minutes}m"
        } else {
            // Time until sunset
            val sunsetDateTime = Instant.ofEpochMilli(sunset).atZone(zoneId)
            val duration = Duration.between(currentDateTime, sunsetDateTime)
            val minutes = duration.toMinutes() % 60
            "${duration.toHours()}h ${minutes}m"
        }
    }

    fun getSubtitleTime(): String {
        val zoneId = ZoneId.systemDefault()
        val formatter = DateTimeFormatter.ofPattern("h:mm a").withZone(zoneId)

        return if (currentTime > sunset || currentTime < sunrise) {
            // Show next sunrise time
            val sunriseDateTime = Instant.ofEpochMilli(sunriseNextDay)
            formatter.format(sunriseDateTime)
        } else {
            // Show today's sunset time
            val sunsetDateTime = Instant.ofEpochMilli(sunset)
            formatter.format(sunsetDateTime)
        }
    }

    fun getProgress(): Float {
        val rawProgress = when {
            currentTime in sunrise until sunset -> {
                // Mid day before sunset
                (currentTime - sunrise).toDouble() / (sunset - sunrise)
            }
            currentTime > sunrise && currentTime > sunset -> {
                // Night time before midnight
                (currentTime - sunset).toDouble() / (sunriseNextDay - sunset)
            }
            currentTime < sunrise -> {
                // Night time after midnight, before sunrise
                (currentTime - sunsetPrevDay).toDouble() / (sunrise - sunsetPrevDay)
            }
            else -> {
                val error = IllegalStateException(
                    "We don't know what to do! " +
                            "[currentTime = $currentTime], " +
                            "[sunrise = $sunrise], " +
                            "[sunset = $sunset], " +
                            "[sunriseNextDay = $sunriseNextDay], " +
                            "[sunsetPrevDay = $sunsetPrevDay]"
                )
                Timber.e(error)

                if (!hasErrorBeenReportedOnce) {
                    FirebaseCrashlytics.getInstance().recordException(error)
                    hasErrorBeenReportedOnce = true
                }

                return 0.0f
            }
        }

        return String.format("%.2f", rawProgress).toFloat()
    }

}