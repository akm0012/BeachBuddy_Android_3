package com.andrew.beachbuddy.ui

import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
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