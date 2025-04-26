package com.andrew.beachbuddy.ui.domainmodels

import com.andrew.beachbuddy.database.model.RequestedItem

data class RequestedItemsDM (
    val nonCompletedItems: List<RequestedItem> = emptyList(),
    val completedItems: List<RequestedItem> = emptyList(),
)
