package com.andrew.beachbuddy.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.beachbuddy.network.dtos.ScoreDto

@Entity
data class Score(

    @PrimaryKey
    var scoreId: String = "",

    var name: String = "",

    var winCount: Int = 0,

    @ColumnInfo(index = true)
    var userId: String = ""
) {
    constructor(scoreDto: ScoreDto) : this() {
        scoreId = scoreDto.id
        name = scoreDto.name
        winCount = scoreDto.winCount
        userId = scoreDto.userId
    }
}