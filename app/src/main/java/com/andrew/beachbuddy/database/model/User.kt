package com.andrew.beachbuddy.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.beachbuddy.network.dtos.UserDto

@Entity
data class User(

    @PrimaryKey
    var userId: String = "",

    var firstName: String = "",

    var lastName: String = "",

    var fullName: String = "",

    var skinType: Int = 1,

    var phoneNumber: String = "",

    var photoUrl: String = "",

    var totalScore: Int = 0,

//    var scores: RealmList<Score> = RealmList()
) {
    constructor(userDto: UserDto) : this() {
        userId = userDto.id
        firstName = userDto.firstName
        lastName = userDto.lastName
        fullName = userDto.fullName
        skinType = userDto.skinType
        phoneNumber = userDto.phoneNumber
        photoUrl = userDto.photoUrl

        for (scoreDto in userDto.scores) {
            totalScore += scoreDto.winCount
//            scores.add(Score(scoreDto))
        }
    }
}
