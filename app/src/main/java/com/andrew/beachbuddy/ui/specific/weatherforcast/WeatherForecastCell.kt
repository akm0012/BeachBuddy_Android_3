package com.andrew.beachbuddy.ui.specific.weatherforcast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.extensions.previewPlaceholder
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun DailyWeatherForecastCell(
    dailyWeatherInfo: DailyWeatherInfo,
    modifier: Modifier = Modifier
) {
    val dailyWeatherUiState = DailyWeatherUiState(dailyWeatherInfo)

    WeatherForecastCell(
        topTitle = dailyWeatherUiState.formattedTime,
        imageUrl = dailyWeatherUiState.iconUrl,
        mainBody = dailyWeatherUiState.feelsLike,
        topSubtitle = dailyWeatherUiState.rain,
        bottomSubtitle = dailyWeatherUiState.summary,
        modifier = modifier
    )
}

@Composable
fun HourlyWeatherForecastCell(
    hourlyWeatherInfo: HourlyWeatherInfo,
    modifier: Modifier = Modifier
) {
    val dailyWeatherUiState = HourlyWeatherUiState(hourlyWeatherInfo)

    WeatherForecastCell(
        topTitle = dailyWeatherUiState.formattedTime,
        imageUrl = dailyWeatherUiState.iconUrl,
        mainBody = dailyWeatherUiState.feelsLike,
        topSubtitle = dailyWeatherUiState.humidity,
        bottomSubtitle = dailyWeatherUiState.summary,
        modifier = modifier
    )
}

@Composable
fun WeatherForecastCell(
    topTitle: String,
    imageUrl: String,
    mainBody: String,
    topSubtitle: String,
    bottomSubtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Text(
            text = topTitle,
            style = MaterialTheme.typography.titleLarge
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(75.dp)
                .previewPlaceholder(
                    Modifier.background(Color.Red)
                )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            Text(
                text = mainBody,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = topSubtitle,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = bottomSubtitle,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun HourlyWeatherForecastCellPreview() {
    BeachBuddyTheme {
        WeatherForecastCell(
            topTitle = "1:00 PM",
            imageUrl = "",
            mainBody = "96°",
            topSubtitle = "50%",
            bottomSubtitle = "Clear"
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun DailyWeatherForecastCellPreview() {
    BeachBuddyTheme {
        WeatherForecastCell(
            topTitle = "Thu",
            imageUrl = "",
            mainBody = "96°",
            topSubtitle = "0.0",
            bottomSubtitle = "Clear",
            modifier = Modifier
                .height(300.dp)
                .width(200.dp)
        )
    }
}