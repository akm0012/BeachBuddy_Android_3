package com.andrew.beachbuddy.network.service

import com.andrew.beachbuddy.network.dtos.DashboardDto
import com.andrew.beachbuddy.network.dtos.RequestedItemDto
import com.andrew.beachbuddy.network.requests.AddDeviceRequest
import com.andrew.beachbuddy.network.requests.AddGameRequest
import com.andrew.beachbuddy.network.requests.UpdateRequestedItemRequest
import com.andrew.beachbuddy.network.requests.UpdateScoreRequest
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class ApiService @Inject constructor(retrofit: Retrofit) {

    private val apiServiceInterface by lazy {
        retrofit.create(ApiServiceEndpointInterface::class.java)
    }

    suspend fun getNotCompletedRequestedItems(): List<RequestedItemDto> {
        return apiServiceInterface.getNonCompletedRequestedItems()
    }

    suspend fun updateRequestedItem(
        requestedItemId: String,
        request: UpdateRequestedItemRequest
    ): RequestedItemDto {
        return apiServiceInterface.updateRequestedItem(requestedItemId, request)
    }

    suspend fun addDevice(addDeviceRequest: AddDeviceRequest) : Response<Unit> {
        return apiServiceInterface.addDevice(addDeviceRequest)
    }

    suspend fun getDashboard(lat: Double, lon: Double): DashboardDto {
        return apiServiceInterface.getDashboard(lat, lon)
    }

    suspend fun updateScore(scoreId: String, updateScoreRequest: UpdateScoreRequest): Response<Unit> {
        return apiServiceInterface.updateScore(scoreId, updateScoreRequest)
    }

    suspend fun addGame(addGameRequest: AddGameRequest): Response<Unit> {
        return apiServiceInterface.addGame(addGameRequest)
    }

    suspend fun refresh(): Response<Unit> {
        return apiServiceInterface.refresh()
    }

    suspend fun setSunScreenReminder(userId: String): Response<Unit> {
        return apiServiceInterface.setSunScreenReminder(userId)
    }
}
