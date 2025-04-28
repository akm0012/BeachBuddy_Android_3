package com.andrew.beachbuddy.ui

import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM

val sampleWeatherDM = WeatherDM(
    currentWeather = CurrentWeather(
        id = "CurrentWeatherPrimaryKey",
        latitude = 27.2678,
        longitude = -82.5537,
        timeUpdated = 1745850765,
        sunrise = 1745837609,
        sunset = 1745884895,
        temp = 79.83,
        feelsLikeTemp = 79.83,
        humidityPercent = 65,
        cloudPercent = 0,
        windSpeed = 5.01,
        windGust = 8.01,
        windDeg = 37,
        mainDescription = "Clear",
        secondaryDescription = "clear sky",
        iconTemplate = "01d"
    ),
    beachConditions = BeachConditions(
        id = "BeachConditionsPrimaryKey",
        timeUpdated = 1745695131000,
        _flagColor = "Green",
        surfCondition = "Calm",
        surfHeight = "0-1 feet",
        respiratoryIrritation = "None",
        jellyFish = "None"
    ),
    uvInfo = CurrentUvInfo(
        id = "CurrentUvInfoPrimaryKey",
        currentUv = 4.3374,
        currentUvTime = "2025-04-28T14:22:08.520Z",
        maxUv = 10.1634,
        maxUvTime = "2025-04-28T17:28:49.777Z",
        safeExposureMinSkin1 = 38,
        safeExposureMinSkin2 = 46,
        safeExposureMinSkin3 = 61,
        safeExposureMinSkin4 = 77,
        safeExposureMinSkin5 = 123,
        safeExposureMinSkin6 = 231
    ),
    locality = "Siesta Key"
)

val sampleDailyWeatherInfoList = listOf(
    DailyWeatherInfo(
        id = 0,
        time = System.currentTimeMillis() / 1000 + 0 * 86400, // today
        feelsLikeDay = 77.5,
        humidity = 65,
        rainMilliMeters = 0.0,
        mainDescription = "Clear",
        secondaryDescription = "Sunny",
        iconTemplate = "01d"
    ),
    DailyWeatherInfo(
        id = 1,
        time = System.currentTimeMillis() / 1000 + 1 * 86400, // tomorrow
        feelsLikeDay = 79.2,
        humidity = 60,
        rainMilliMeters = 1.2,
        mainDescription = "Clouds",
        secondaryDescription = "Partly Cloudy",
        iconTemplate = "02d"
    ),
    DailyWeatherInfo(
        id = 2,
        time = System.currentTimeMillis() / 1000 + 2 * 86400,
        feelsLikeDay = 75.1,
        humidity = 70,
        rainMilliMeters = 5.4,
        mainDescription = "Rain",
        secondaryDescription = "Light Showers",
        iconTemplate = "10d"
    ),
    DailyWeatherInfo(
        id = 3,
        time = System.currentTimeMillis() / 1000 + 3 * 86400,
        feelsLikeDay = 73.0,
        humidity = 80,
        rainMilliMeters = 10.0,
        mainDescription = "Thunderstorm",
        secondaryDescription = "Scattered Thunderstorms",
        iconTemplate = "11d"
    ),
    DailyWeatherInfo(
        id = 4,
        time = System.currentTimeMillis() / 1000 + 4 * 86400,
        feelsLikeDay = 78.8,
        humidity = 55,
        rainMilliMeters = 0.0,
        mainDescription = "Clear",
        secondaryDescription = "Mostly Sunny",
        iconTemplate = "01d"
    ),
    DailyWeatherInfo(
        id = 5,
        time = System.currentTimeMillis() / 1000 + 5 * 86400,
        feelsLikeDay = 81.6,
        humidity = 50,
        rainMilliMeters = 0.3,
        mainDescription = "Clouds",
        secondaryDescription = "Overcast",
        iconTemplate = "04d"
    )
)

val sampleHourlyWeatherInfoList = listOf(
    HourlyWeatherInfo(
        id = 0,
        time = System.currentTimeMillis() / 1000 + 0 * 3600, // now
        temp = 78.0,
        feelsLike = 79.5,
        humidity = 60,
        clouds = 10,
        windSpeed = 5.2,
        mainDescription = "Clear",
        secondaryDescription = "Sunny",
        iconTemplate = "01d"
    ),
    HourlyWeatherInfo(
        id = 1,
        time = System.currentTimeMillis() / 1000 + 1 * 3600, // +1 hour
        temp = 76.5,
        feelsLike = 77.0,
        humidity = 65,
        clouds = 20,
        windSpeed = 6.0,
        mainDescription = "Clouds",
        secondaryDescription = "Partly Cloudy",
        iconTemplate = "02d"
    ),
    HourlyWeatherInfo(
        id = 2,
        time = System.currentTimeMillis() / 1000 + 2 * 3600,
        temp = 74.0,
        feelsLike = 74.5,
        humidity = 70,
        clouds = 40,
        windSpeed = 4.8,
        mainDescription = "Clouds",
        secondaryDescription = "Mostly Cloudy",
        iconTemplate = "03d"
    ),
    HourlyWeatherInfo(
        id = 3,
        time = System.currentTimeMillis() / 1000 + 3 * 3600,
        temp = 72.0,
        feelsLike = 71.5,
        humidity = 75,
        clouds = 90,
        windSpeed = 3.5,
        mainDescription = "Rain",
        secondaryDescription = "Light Showers",
        iconTemplate = "10d"
    ),
    HourlyWeatherInfo(
        id = 4,
        time = System.currentTimeMillis() / 1000 + 4 * 3600,
        temp = 71.0,
        feelsLike = 70.0,
        humidity = 80,
        clouds = 100,
        windSpeed = 7.0,
        mainDescription = "Thunderstorm",
        secondaryDescription = "Isolated Thunderstorms",
        iconTemplate = "11d"
    ),
    HourlyWeatherInfo(
        id = 5,
        time = System.currentTimeMillis() / 1000 + 5 * 3600,
        temp = 75.0,
        feelsLike = 75.8,
        humidity = 68,
        clouds = 50,
        windSpeed = 5.5,
        mainDescription = "Clouds",
        secondaryDescription = "Overcast",
        iconTemplate = "04d"
    )
)