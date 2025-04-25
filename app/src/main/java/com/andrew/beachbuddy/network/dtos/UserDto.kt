package com.andrew.beachbuddy.network.dtos

data class UserDto(

    val id: String,

    val firstName: String,

    val lastName: String,

    val fullName: String,

    val skinType: Int,

    val phoneNumber: String,

    val photoUrl: String,

    val scores: List<ScoreDto>
)