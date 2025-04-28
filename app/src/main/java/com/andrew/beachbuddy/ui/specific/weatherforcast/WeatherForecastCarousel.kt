package com.andrew.beachbuddy.ui.specific.weatherforcast

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.DarkLightTabletPreviews
import com.andrew.beachbuddy.ui.sampleDailyWeatherInfoList
import com.andrew.beachbuddy.ui.sampleHourlyWeatherInfoList
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun HourlyWeatherForecastCarousel(
    hourlyWeatherInfo: List<HourlyWeatherInfo>,
    modifier: Modifier = Modifier
) {
    // Convert the Hourly Weather Info into Ui State.
    val hourlyWeatherUiStateList = hourlyWeatherInfo.map {
        HourlyWeatherUiState(it)
    }

    WeatherForecastCarousel(weatherData = hourlyWeatherUiStateList, modifier = modifier)
}

@Composable
fun DailyWeatherForecastCarousel(
    dailyWeatherInfo: List<DailyWeatherInfo>,
    modifier: Modifier = Modifier
) {
    // Convert the Hourly Weather Info into Ui State.
    val dailyWeatherUiStateList = dailyWeatherInfo.map {
        DailyWeatherUiState(it)
    }

    WeatherForecastCarousel(weatherData = dailyWeatherUiStateList, modifier = modifier)
}

@Composable
fun WeatherForecastCarousel(
    weatherData: List<WeatherUiState>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(items = weatherData, key = { it.id }) { weatherUiState ->
            WeatherForecastCell(
                topTitle = weatherUiState.topTitle,
                imageUrl = weatherUiState.imageUrl,
                mainBody = weatherUiState.mainBody,
                topSubtitle = weatherUiState.topSubtitle,
                bottomSubtitle = weatherUiState.bottomSubtitle
            )
        }
    }
}

@DarkLightTabletPreviews
@Composable
private fun DailyWeatherForecastCarouselPreview() {
    BeachBuddyTheme {
        DailyWeatherForecastCarousel(
            dailyWeatherInfo = sampleDailyWeatherInfoList
        )

    }
}

@DarkLightTabletPreviews
@Composable
private fun HourlyWeatherForecastCarouselPreview() {
    BeachBuddyTheme {
        HourlyWeatherForecastCarousel(
            hourlyWeatherInfo = sampleHourlyWeatherInfoList
        )

    }
}