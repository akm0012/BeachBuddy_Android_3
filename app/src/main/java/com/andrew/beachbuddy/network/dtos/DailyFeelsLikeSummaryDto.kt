package com.andrew.beachbuddy.network.dtos

data class DailyFeelsLikeSummaryDto(

    var day: Double,

    var night: Double,

    var eve: Double,

    var morn: Double
)