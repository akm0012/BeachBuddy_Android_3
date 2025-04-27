package com.andrew.beachbuddy.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.beachbuddy.enums.FlagColor
import com.andrew.beachbuddy.enums.getFlagColorEnum
import com.andrew.beachbuddy.network.dtos.BeachConditionsDto
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Entity
data class BeachConditions(

    @PrimaryKey
    var id: String = "BeachConditionsPrimaryKey",

    var timeUpdated: Long = 0,

    var _flagColor: String = "Green",

    var surfCondition: String = "",

    var surfHeight: String = "",

    var respiratoryIrritation: String = "",

    var jellyFish: String = "",
) {

    val flagColor: FlagColor
        get() = _flagColor.getFlagColorEnum()

    constructor(beachConditionsDto: BeachConditionsDto) : this() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX") // Example: "2025-04-26T19:18:51+00:00"

        val zonedDateTime = ZonedDateTime
            .parse(beachConditionsDto.updatedTime, formatter)
            .withZoneSameInstant(ZoneOffset.UTC)

        timeUpdated = zonedDateTime.toInstant().toEpochMilli()

        _flagColor = beachConditionsDto.flagColor
        surfCondition = beachConditionsDto.surfCondition
        surfHeight = beachConditionsDto.surfHeight
        respiratoryIrritation = beachConditionsDto.respiratoryIrritation
        jellyFish = beachConditionsDto.jellyFishPresent
    }

}
