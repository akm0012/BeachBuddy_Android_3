package com.andrew.beachbuddy.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.beachbuddy.network.dtos.WeatherInfoDto
import java.time.Instant
import java.time.ZoneId

@Entity
data class SunsetInfo(
    @PrimaryKey
    var id: String = "SunsetInfoPrimaryKey",

    var sunrise: Long = 0,

    var sunset: Long = 0,

    var sunriseNextDay: Long = 0,

    var sunsetPrevDay: Long = 0
) {
    constructor(weatherInfoDto: WeatherInfoDto) : this() {

        sunrise =
            Instant.ofEpochSecond(weatherInfoDto.current.sunrise).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()


        sunset =
            Instant.ofEpochSecond(weatherInfoDto.current.sunset).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        // Fixme: could make this more accurate by not assuming sunset/sunrise times
        sunriseNextDay = Instant.ofEpochMilli(sunrise).atZone(ZoneId.systemDefault()).plusDays(1).toInstant().toEpochMilli()
        sunsetPrevDay = Instant.ofEpochMilli(sunset).atZone(ZoneId.systemDefault()).minusDays(1).toInstant().toEpochMilli()
    }
}
