package com.andrew.beachbuddy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andrew.beachbuddy.database.dao.BeachConditionsDao
import com.andrew.beachbuddy.database.dao.UserDao
import com.andrew.beachbuddy.database.dao.WeatherDao
import com.andrew.beachbuddy.database.model.*

@Database(
    entities = [
        BeachConditions::class,
        CurrentUvInfo::class,
        CurrentWeather::class,
        DailyWeatherInfo::class,
        HourlyWeatherInfo::class,
        RequestedItem::class,
        Score::class,
        SunsetInfo::class,
        User::class,
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun beachConditionsDao(): BeachConditionsDao
    abstract fun userDao(): UserDao
    abstract fun weatherDao(): WeatherDao
}