package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.extensions.previewPlaceholder
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.specific.uvindex.sunprogress.SunProgressRealistic
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@Composable
fun SunProgressAndUV(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = "UV Index",
                color = colorResource(R.color.dashboard_text),
                fontSize = TextUnit(14f, TextUnitType.Sp)
            )
            Text(
                text = "8.86",
                color = colorResource(R.color.dashboard_text_dark),
                fontSize = TextUnit(20f, TextUnitType.Sp),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        val zoneId = ZoneId.of("America/New_York")
        val date = LocalDate.of(2025, 7, 23)

        fun timeToMillis(hour: Int, minute: Int): Long {
            val localDateTime = LocalDateTime.of(date, LocalTime.of(hour, minute))
            return localDateTime.atZone(zoneId).toInstant().toEpochMilli()
        }

        val sunriseMillis = timeToMillis(6, 30)
        val solarNoonMillis = timeToMillis(13, 39)
        val sunsetMillis = timeToMillis(20, 30)
        val currentTimeMillis = timeToMillis(9, 39)

        SunProgressRealistic(
            sunrise = sunriseMillis,
            solarNoon = solarNoonMillis,
            sunset = sunsetMillis,
            currentTime = currentTimeMillis,
            modifier = Modifier.previewPlaceholder(Modifier.height(100.dp))
        )
    }

}

@DarkLightPhonePreviews
@Composable
private fun SunProgressAndUVPreview() {
    BeachBuddyTheme {

        SunProgressAndUV()
    }

}