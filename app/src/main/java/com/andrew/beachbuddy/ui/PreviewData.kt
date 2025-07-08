package com.andrew.beachbuddy.ui

import com.andrew.beachbuddy.database.model.BeachConditions
import com.andrew.beachbuddy.database.model.CurrentUvInfo
import com.andrew.beachbuddy.database.model.CurrentWeather
import com.andrew.beachbuddy.database.model.DailyWeatherInfo
import com.andrew.beachbuddy.database.model.HourlyWeatherInfo
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.database.model.UserWithScores
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

val sampleUserWithScoresList = listOf(
    UserWithScores(
        user = User(
            userId = "user_1",
            firstName = "Andrew",
            lastName = "Marshall",
            fullName = "Andrew Marshall",
            skinType = 2,
            phoneNumber = "123-456-7890",
            photoUrl = "https://example.com/photos/alice.jpg",
            totalScore = 150
        ),
        scores = listOf(
            Score(scoreId = "score_1a", name = "Beach Challenge", winCount = 5, userId = "user_1"),
            Score(scoreId = "score_1b", name = "Sun Trivia", winCount = 3, userId = "user_1")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_2",
            firstName = "Bob",
            lastName = "Johnson",
            fullName = "Bob Johnson",
            skinType = 3,
            phoneNumber = "234-567-8901",
            photoUrl = "https://example.com/photos/bob.jpg",
            totalScore = 200
        ),
        scores = listOf(
            Score(scoreId = "score_2a", name = "Beach Challenge", winCount = 8, userId = "user_2"),
            Score(scoreId = "score_2b", name = "Surf Quiz", winCount = 2, userId = "user_2")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_3",
            firstName = "Carol",
            lastName = "Williams",
            fullName = "Carol Williams",
            skinType = 1,
            phoneNumber = "345-678-9012",
            photoUrl = "https://example.com/photos/carol.jpg",
            totalScore = 90
        ),
        scores = listOf(
            Score(scoreId = "score_3a", name = "Sun Trivia", winCount = 4, userId = "user_3")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_4",
            firstName = "David",
            lastName = "Brown",
            fullName = "David Brown",
            skinType = 4,
            phoneNumber = "456-789-0123",
            photoUrl = "https://example.com/photos/david.jpg",
            totalScore = 120
        ),
        scores = listOf(
            Score(scoreId = "score_4a", name = "Beach Challenge", winCount = 6, userId = "user_4"),
            Score(scoreId = "score_4b", name = "Surf Quiz", winCount = 1, userId = "user_4"),
            Score(scoreId = "score_4c", name = "Sun Trivia", winCount = 2, userId = "user_4")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_5",
            firstName = "Eve",
            lastName = "Davis",
            fullName = "Eve Davis",
            skinType = 2,
            phoneNumber = "567-890-1234",
            photoUrl = "https://example.com/photos/eve.jpg",
            totalScore = 180
        ),
        scores = listOf(
            Score(scoreId = "score_5a", name = "Beach Challenge", winCount = 7, userId = "user_5")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_6",
            firstName = "Frank",
            lastName = "Miller",
            fullName = "Frank Miller",
            skinType = 5,
            phoneNumber = "678-901-2345",
            photoUrl = "https://example.com/photos/frank.jpg",
            totalScore = 300
        ),
        scores = listOf(
            Score(scoreId = "score_6a", name = "Surf Quiz", winCount = 10, userId = "user_6"),
            Score(scoreId = "score_6b", name = "Beach Challenge", winCount = 5, userId = "user_6")
        )
    )
)

val mockUserWithScoresList = listOf(
    UserWithScores(
        user = User(
            userId = "user_1",
            firstName = "Alice",
            lastName = "Smith",
            fullName = "Alice Smith",
            skinType = 2,
            phoneNumber = "123-456-7890",
            photoUrl = "https://example.com/photos/alice.jpg",
            totalScore = 18
        ),
        scores = listOf(
            Score(scoreId = "score_1a", name = "Beach Volleyball", winCount = 8, userId = "user_1"),
            Score(scoreId = "score_1b", name = "Sun Trivia", winCount = 6, userId = "user_1"),
            Score(scoreId = "score_1c", name = "Surf Quiz", winCount = 4, userId = "user_1")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_2",
            firstName = "Bob",
            lastName = "Johnson",
            fullName = "Bob Johnson",
            skinType = 4,
            phoneNumber = "234-567-8901",
            photoUrl = "https://example.com/photos/bob.jpg",
            totalScore = 14
        ),
        scores = listOf(
            Score(scoreId = "score_2a", name = "Beach Volleyball", winCount = 4, userId = "user_2"),
            Score(scoreId = "score_2b", name = "Sun Trivia", winCount = 5, userId = "user_2"),
            Score(scoreId = "score_2c", name = "Surf Quiz", winCount = 5, userId = "user_2")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_3",
            firstName = "Carol",
            lastName = "Nguyen",
            fullName = "Carol Nguyen",
            skinType = 1,
            phoneNumber = "345-678-9012",
            photoUrl = "https://example.com/photos/carol.jpg",
            totalScore = 12
        ),
        scores = listOf(
            Score(scoreId = "score_3a", name = "Beach Volleyball", winCount = 5, userId = "user_3"),
            Score(scoreId = "score_3b", name = "Sun Trivia", winCount = 2, userId = "user_3"),
            Score(scoreId = "score_3c", name = "Surf Quiz", winCount = 5, userId = "user_3")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_4",
            firstName = "David",
            lastName = "Lee",
            fullName = "David Lee",
            skinType = 3,
            phoneNumber = "456-789-0123",
            photoUrl = "https://example.com/photos/david.jpg",
            totalScore = 9
        ),
        scores = listOf(
            Score(scoreId = "score_4a", name = "Beach Volleyball", winCount = 3, userId = "user_4"),
            Score(scoreId = "score_4b", name = "Sun Trivia", winCount = 1, userId = "user_4"),
            Score(scoreId = "score_4c", name = "Surf Quiz", winCount = 5, userId = "user_4")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_5",
            firstName = "Eve",
            lastName = "Martinez",
            fullName = "Eve Martinez",
            skinType = 5,
            phoneNumber = "567-890-1234",
            photoUrl = "https://example.com/photos/eve.jpg",
            totalScore = 20
        ),
        scores = listOf(
            Score(scoreId = "score_5a", name = "Beach Volleyball", winCount = 7, userId = "user_5"),
            Score(scoreId = "score_5b", name = "Sun Trivia", winCount = 6, userId = "user_5"),
            Score(scoreId = "score_5c", name = "Surf Quiz", winCount = 7, userId = "user_5")
        )
    ),
    UserWithScores(
        user = User(
            userId = "user_6",
            firstName = "Frank",
            lastName = "Miller",
            fullName = "Frank Miller",
            skinType = 2,
            phoneNumber = "678-901-2345",
            photoUrl = "https://example.com/photos/frank.jpg",
            totalScore = 11
        ),
        scores = listOf(
            Score(scoreId = "score_6a", name = "Beach Volleyball", winCount = 1, userId = "user_6"),
            Score(scoreId = "score_6b", name = "Sun Trivia", winCount = 5, userId = "user_6"),
            Score(scoreId = "score_6c", name = "Surf Quiz", winCount = 5, userId = "user_6")
        )
    )
)