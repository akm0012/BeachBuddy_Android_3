package com.andrew.beachbuddy.inject

import android.content.Context
import androidx.room.Room
import com.andrew.beachbuddy.database.AppDatabase
import com.andrew.beachbuddy.database.dao.BeachConditionsDao
import com.andrew.beachbuddy.database.dao.UserDao
import com.andrew.beachbuddy.database.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "BeachBuddy-Database"
        ).build()
    }

    @Provides
    fun provideBeachConditionsDao(db: AppDatabase): BeachConditionsDao {
        return db.beachConditionsDao()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherDao {
        return db.weatherDao()
    }

}