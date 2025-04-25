package com.andrew.beachbuddy.network.dtos

import com.andrew.beachbuddy.network.dtos.BeachConditionsDto
import com.andrew.beachbuddy.network.dtos.CurrentUvDto
import com.google.gson.annotations.SerializedName

data class DashboardDto(

    var users: List<UserDto>,

    var beachConditions: BeachConditionsDto,

    @SerializedName("dashboardUvDto")
    var currentUvDto: CurrentUvDto,

    @SerializedName("weatherInfo")
    var weatherDto: WeatherInfoDto

)