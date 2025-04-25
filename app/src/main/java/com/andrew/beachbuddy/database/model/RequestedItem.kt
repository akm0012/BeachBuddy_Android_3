package com.andrew.beachbuddy.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andrew.beachbuddy.extensions.convertServerTimeToMillis
import com.andrew.beachbuddy.network.dtos.RequestedItemDto

@Entity
data class RequestedItem(

    @PrimaryKey
    var id: String = "",

    var name: String = "",

    var count: Int = 0,

    var isComplete: Boolean = false,

    var createdAtTime: Long = 0L,

    var completedAtTime: Long? = null,

    @ColumnInfo(index = true)
    var requestorId: String = "",

    var requestorFirstName: String = "",

    var requestorLastName: String = "",

    var requestorPhotoUrl: String = ""
) {
    constructor(itemDto: RequestedItemDto) : this() {
        id = itemDto.id
        name = itemDto.name
        count = itemDto.count
        isComplete = itemDto.isRequestCompleted
        createdAtTime = itemDto.createdDateTime.convertServerTimeToMillis()
        requestorId = itemDto.requestedByUserId
        requestorFirstName = itemDto.requestedByUser.firstName
        requestorLastName = itemDto.requestedByUser.lastName
        requestorPhotoUrl = itemDto.requestedByUser.photoUrl

        if (itemDto.completedDateTime != null) {
            completedAtTime = itemDto.completedDateTime.convertServerTimeToMillis()
        }
    }

    fun getNameAndQuantity() =
        if (count > 1) {
            "($count) $name"
        } else {
            name
        }

}