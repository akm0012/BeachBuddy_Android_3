package com.andrew.beachbuddy.ui.specific.currentweather

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.extensions.previewPlaceholder
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding


@Composable
fun CurrentWeatherComposable(
    currentWeather: WeatherDM,
    modifier: Modifier = Modifier
) {

    val currentWeatherUiState = CurrentWeatherUiState(currentWeather)

    CurrentWeatherComposable(
        cityName = currentWeatherUiState.getCityName(),
        description = currentWeatherUiState.getWeatherDescription(),
        iconUrl = currentWeatherUiState.getIconUrl(),
        feelsLikeTemp = currentWeatherUiState.getFeelsLikeTemp(),
        backgroundColor = currentWeatherUiState.getCardBackgroundColor(),
        textColor = currentWeatherUiState.getTextColor(),
        secondaryTextColor = currentWeatherUiState.getSecondaryTextColor(),
        modifier = modifier
    )
}

@Composable
fun CurrentWeatherComposable(
    cityName: String,
    description: String,
    iconUrl: String,
    feelsLikeTemp: String,
    @ColorRes backgroundColor: Int,
    @ColorRes textColor: Int,
    @ColorRes secondaryTextColor: Int,
    modifier: Modifier = Modifier
) {
    BeachBuddyCard(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(backgroundColor)
        ),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.padding(start = StandardPadding, top = StandardPadding)) {

                Text(
                    text = cityName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(textColor),
                    )
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = colorResource(secondaryTextColor),
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(iconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                        .previewPlaceholder(
                            Modifier.background(Color.Red)
                        )
                )
            }

            Text(
                text = feelsLikeTemp,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = colorResource(textColor),
                    fontSize = TextUnit(36f, TextUnitType.Sp)

                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = StandardPadding, bottom = 10.dp)

            )
        }
    }
}


@Preview
@Composable
private fun CurrentWeatherPreview() {
    BeachBuddyTheme {
        CurrentWeatherComposable(
            cityName = "Siesta Key",
            description = "Sunny and Perfect!",
            iconUrl = "",
            feelsLikeTemp = "96°",
            backgroundColor = R.color.flag_green,
            textColor = R.color.white,
            secondaryTextColor = R.color.white,
            modifier = Modifier
                .width(230.dp)
                .height(150.dp)
        )
    }
}