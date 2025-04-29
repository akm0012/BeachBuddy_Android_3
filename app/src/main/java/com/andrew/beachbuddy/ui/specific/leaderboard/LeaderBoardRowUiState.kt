package com.andrew.beachbuddy.ui.specific.leaderboard

import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.database.model.UserWithScores
import com.andrew.beachbuddy.inject.BASE_ENDPOINT

class LeaderBoardRowUiState(
    userWithScore: UserWithScores
) {

    private val user = userWithScore.user
    private val scores = userWithScore.scores

    val name = user.firstName

    fun getSubtitle(): String {

        var highScore = Score()

        for (score in scores) {
            if (score.winCount > highScore.winCount) {
                highScore = score
            }
        }

        val highScoreGame = when (highScore.winCount) {
            0 -> return "No wins yet..."
            else -> highScore.name
        }

        return "$highScoreGame (${highScore.winCount})"
    }

    fun getScore(): String {

        var totalWinCount = 0

        for (score in scores) {
            totalWinCount += score.winCount
        }

        return "$totalWinCount"
    }

    val profilePhotoUrl = "${BASE_ENDPOINT}${user.photoUrl}"

}