package com.andrew.beachbuddy.ui.specific.requesteditem

import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.inject.BASE_ENDPOINT
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class RequestedItemUiState(
    requestedItem: RequestedItem
) {

    val titleText = if (requestedItem.count > 1) {
        "${requestedItem.name} (${requestedItem.count})"
    } else {
        requestedItem.name
    }

    val subTitleText = {
        val dayAndTime = Instant.ofEpochMilli(requestedItem.createdAtTime)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("EE h:mm a", Locale.getDefault()))

        "${requestedItem.requestorFirstName} • $dayAndTime"
    }

    val profilePhotoUrl = "$BASE_ENDPOINT${requestedItem.requestorPhotoUrl}"

    val isComplete = requestedItem.isComplete
}