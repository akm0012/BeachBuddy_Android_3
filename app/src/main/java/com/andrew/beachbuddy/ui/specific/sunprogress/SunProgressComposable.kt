package com.andrew.beachbuddy.ui.specific.sunprogress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun SunProgressRealistic(
    sunrise: Long,
    solarNoon: Long,
    sunset: Long,
    currentTime: Long,
    modifier: Modifier = Modifier
) {

    val extremeUVColor = colorResource(id = R.color.uv_extreme)

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        val width = size.width
        val height = size.height

        val totalDuration = (sunset - sunrise).coerceAtLeast(1)
        val noonOffset = (solarNoon - sunrise).coerceIn(0, totalDuration)
        val progressMillis = (currentTime - sunrise).coerceIn(0, totalDuration)

        val noonRatio = noonOffset.toFloat() / totalDuration
        val progressRatio = progressMillis.toFloat() / totalDuration

        // Draw skewed bell curve
        val path = Path().apply {
            moveTo(0f, height)
            for (step in 0..100) {
                val t = step / 100f // normalized progress across the X-axis
                val y = if (t < noonRatio) {
                    // sunrise → solar noon
                    val localT = t / noonRatio
                    1f - sin(localT * PI / 2f).toFloat()
                } else {
                    // solar noon → sunset
                    val localT = (t - noonRatio) / (1f - noonRatio)
                    1f - sin((1f - localT) * PI / 2f).toFloat()
                }
                lineTo(width * t, height * y)
            }
            lineTo(width, height)
            close()
        }


        drawPath(
            path = path,
            color = extremeUVColor,
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    Color.Transparent,
//                    extremeUVColor.copy(alpha = 0.5f),
//                ),
//                startY = 0f,
//                endY = size.height
//            ),
            style = Stroke(width = 2.dp.toPx())
        )

        drawPath(
            path = path,
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    extremeUVColor.copy(alpha = 0.5f),
                ),
                startY = 0f,
                endY = size.height
            )
        )

        // Sun position along the path
        val sunX = width * progressRatio
        val sunY = if (progressRatio < noonRatio) {
            val localT = progressRatio / noonRatio
            height * (1f - sin(localT * PI / 2f).toFloat())
        } else {
            val localT = (progressRatio - noonRatio) / (1f - noonRatio)
            height * (1f - sin((1f - localT) * PI / 2f).toFloat())
        }


        drawCircle(
            color = Color.Yellow,
            radius = 12f,
            center = Offset(sunX, sunY)
        )
    }
}

@Composable
fun SunDot(
    center: Offset,
    radius: Float,
    color: Color = Color.Yellow,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size((radius * 2).dp)) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    color,
                    color.copy(alpha = 0.0f)
                ),
                center = Offset(radius, radius),
                radius = radius
            ),
            center = Offset(radius, radius),
            radius = radius
        )
    }
}

/**
 * @param sunrise in epoch millis
 * @param sunset in epoch millis
 * @param solarNoon in epoch millis
 * @param currentTime in epoch millis
 */
@Composable
fun SunProgressSimple(
    sunrise: Long,
    solarNoon: Long,
    sunset: Long,
    currentTime: Long,
    modifier: Modifier = Modifier
) {

    val zoneId = ZoneId.systemDefault()
    val sunriseDateTime = Instant.ofEpochMilli(sunrise).atZone(zoneId)
    val sunsetDateTime = Instant.ofEpochMilli(sunset).atZone(zoneId)
    val solarNoonDateTime = Instant.ofEpochMilli(solarNoon).atZone(zoneId)
    val currentTimeDateTime = Instant.ofEpochMilli(currentTime).atZone(zoneId)

    val progress = remember(currentTime) {
        val totalMinutes = Duration.between(sunriseDateTime, sunsetDateTime).toMinutes().toFloat()
        val currentMinutes =
            Duration.between(sunriseDateTime, currentTimeDateTime).toMinutes().toFloat()
                .coerceIn(0f, totalMinutes)
        currentMinutes / totalMinutes
    }

    // TODO: There is some strange sizing stuff going on here. Will play with later once we see how it fits in the UI.
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {

            val width = size.width
            val height = size.height

            // Draw the bell curve
            val path = Path().apply {

                moveTo(0f, height)
                for (x in 0..100) {
                    val t = x / 100f
                    val y = height * (1f - sin(PI * t).toFloat())
                    lineTo(width * t, y)
                }

                lineTo(width, height)
                close()
            }
            drawPath(path, color = Color.Yellow.copy(alpha = 0.3f))

            // Draw the sun's current position
            val sunX = width * progress
            val sunY = height * (1f - sin(PI * progress).toFloat())

            drawCircle(
                color = Color.Yellow,
                radius = 12f,
                center = Offset(sunX, sunY)
            )
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun SunProgressRealisticPreview() {
    BeachBuddyTheme {
        val zoneId = ZoneId.of("America/New_York")
        val date = LocalDate.of(2025, 7, 23)

        fun timeToMillis(hour: Int, minute: Int): Long {
            val localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute))
            return localDateTime.atZone(zoneId).toInstant().toEpochMilli()
        }

        val sunriseMillis = timeToMillis(6, 30)
        val solarNoonMillis = timeToMillis(13, 39)
        val sunsetMillis = timeToMillis(20, 30)
        val currentTimeMillis = timeToMillis(13, 39)

        SunProgressRealistic(
            sunrise = sunriseMillis,
            solarNoon = solarNoonMillis,
            sunset = sunsetMillis,
            currentTime = currentTimeMillis,
            modifier = Modifier.height(100.dp)
        )
    }
}


@DarkLightPhonePreviews
@Composable
private fun SunProgressPreview() {
    BeachBuddyTheme {
        val zoneId = ZoneId.of("America/New_York")
        val date = LocalDate.of(2025, 7, 23)

        fun timeToMillis(hour: Int, minute: Int): Long {
            val localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute))
            return localDateTime.atZone(zoneId).toInstant().toEpochMilli()
        }

        val sunriseMillis = timeToMillis(6, 30)
        val solarNoonMillis = timeToMillis(13, 39)
        val sunsetMillis = timeToMillis(20, 30)
        val currentTimeMillis = timeToMillis(13, 39)

        SunProgressSimple(
            sunrise = sunriseMillis,
            solarNoon = solarNoonMillis,
            sunset = sunsetMillis,
            currentTime = currentTimeMillis,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        )
    }

}