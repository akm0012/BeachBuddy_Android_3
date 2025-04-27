package com.andrew.beachbuddy.repository

import com.andrew.beachbuddy.database.dao.UserDao
import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.network.requests.UpdateRequestedItemRequest
import com.andrew.beachbuddy.network.service.ApiService
import com.andrew.beachbuddy.ui.domainmodels.RequestedItemsDM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestedItemRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) {

    private val _newItemsAddedFlow = MutableSharedFlow<RequestedItem>()
    val newItemsFlow = _newItemsAddedFlow.asSharedFlow()

    private val _errorFlow = MutableSharedFlow<RequestedItemError>()
    val errorFlow = _errorFlow.asSharedFlow()


    fun getRequestedItemsDomainModel(): Flow<RequestedItemsDM> {
        GlobalScope.launch {
            refreshRequestedItems()
        }

        val todayStartOfDay =
            ZonedDateTime.now(ZoneId.systemDefault())
                .toLocalDate()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()

        val tomorrowStartOfDay =
            ZonedDateTime.now(ZoneId.systemDefault())
                .toLocalDate()
                .plusDays(1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()

        return userDao.getNotCompletedRequestedItems()
            .zip(
                userDao.getCompletedTodayRequestedItems(
                    todayStartOfDay,
                    tomorrowStartOfDay
                )
            ) { notCompletedItems, completedItems ->
                RequestedItemsDM(notCompletedItems, completedItems)
            }
    }

    /**
     * @param itemIdFromNotification The name of the item added. Only use this when refreshing
     *                                   items from a Push Notification.
     */
    suspend fun refreshRequestedItems(itemIdFromNotification: String? = null) {
        try {
            val itemDtos = apiService.getNotCompletedRequestedItems()

            val itemsToSave = ArrayList<RequestedItem>()
            itemDtos.forEach {
                try {
                    val itemToAdd = RequestedItem(it)
                    itemsToSave.add(itemToAdd)
                    if (itemToAdd.id == itemIdFromNotification) {
                        _newItemsAddedFlow.emit(itemToAdd)
                    }
                } catch (e: Exception) {
                    Timber.w(e, "Unable to process item. Skipping it. $it")
                }
            }
            userDao.insertRequestedItems(itemsToSave)

            // Purge all the invalid Items that were deleted else where
            val invalidItems = userDao.getNotCompletedRequestedItems().first().toMutableList()
            itemsToSave.forEach {
                // Remove any valid items that were just pulled down
                invalidItems.remove(it)
            }
            userDao.deleteRequestedItems(invalidItems)

        } catch (cause: Throwable) {
            Timber.w(cause, "Unable to refresh the requested items.")
            sendErrorToChannel("Unable to refresh the requested items.", cause)
        }
    }

    suspend fun markRequestedItemAsComplete(requestedItem: RequestedItem) {

        val requestedItemId = requestedItem.id
        val updateItemRequest = UpdateRequestedItemRequest(
            name = requestedItem.name,
            count = requestedItem.count
        )

        try {
            val itemDto = apiService.updateRequestedItem(requestedItemId, updateItemRequest)
            userDao.insertRequestedItem(RequestedItem(itemDto))
            Timber.d("${itemDto.name} marked as completed.")
        } catch (cause: Exception) {
            Timber.w(cause, "Unable to mark the item as complete.")
            throw cause
        }
    }

    /**
     * Deletes all Requested Items that were completed before the start of today.
     */
    suspend fun deleteAllOldCompletedItems() {

        val todayStartOfDay =
            ZonedDateTime.now(ZoneId.systemDefault())
                .toLocalDate()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()

        try {
            val oldCompletedItems = userDao.getOldCompletedRequestedItems(todayStartOfDay).first()
            userDao.deleteRequestedItems(oldCompletedItems)
        } catch (cause: Exception) {
            Timber.w(cause, "Unable to delete old completed Items.")
        }
    }

    private suspend fun sendErrorToChannel(message: String, cause: Throwable?) {
        val errorMessage = cause?.let { "${cause.localizedMessage}: $message" } ?: message

        Timber.w(cause)

        _errorFlow.emit(RequestedItemError(errorMessage, cause))
    }

    class RequestedItemError(message: String, cause: Throwable?) : Throwable(message, cause)

}
