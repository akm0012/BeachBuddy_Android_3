package com.andrew.beachbuddy.repository

import com.andrew.beachbuddy.database.dao.UserDao
import com.andrew.beachbuddy.network.requests.AddGameRequest
import com.andrew.beachbuddy.network.requests.UpdateScoreRequest
import com.andrew.beachbuddy.network.service.ApiService
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScoreRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val dashboardRepository: DashboardRepository,
) {

    suspend fun updateScore(scoreId: String, winCount: Int) {

        val originalScore = userDao.getScore(scoreId).first()

        try {
            // Assume the update is going to work and update database now
            userDao.insertScore(originalScore.copy(winCount = winCount))

            apiService.updateScore(scoreId, UpdateScoreRequest(winCount))

        } catch (e: Exception) {
            Timber.w(e, "Score update failed. Reverting score.")
            userDao.insertScore(originalScore)
            throw e
        }
    }

    suspend fun addGame(gameName: String) {
        try {
            apiService.addGame(AddGameRequest(gameName))
            dashboardRepository.refreshDashboard()
        } catch (e: Exception) {
            Timber.w(e, "Unable to add the game.")
            throw e
        }
    }

}