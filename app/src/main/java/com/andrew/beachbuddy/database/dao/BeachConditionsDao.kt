package com.andrew.beachbuddy.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrew.beachbuddy.database.model.BeachConditions
import kotlinx.coroutines.flow.Flow

@Dao
interface BeachConditionsDao {

    @Query("SELECT * FROM BeachConditions WHERE id = 'BeachConditionsPrimaryKey'")
    fun getBeachConditions(): Flow<BeachConditions?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBeachConditions(beachConditions: BeachConditions)

}