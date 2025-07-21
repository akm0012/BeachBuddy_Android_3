package com.andrew.beachbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.beachbuddy.database.model.Score
import com.andrew.beachbuddy.repository.DashboardRepository
import com.andrew.beachbuddy.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScoreManagementViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    // todo: Listen for errors
    private val _errorFlow = MutableSharedFlow<ScoreManagementError>()
    val errorFlow = _errorFlow.asSharedFlow()

    val usersWithScores = dashboardRepository.userWithScoresFlow.map {
        it.map { userWithScores ->
            userWithScores.copy(
                scores = userWithScores.scores.sortedBy { score -> score.name }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun onAddNewGame(gameName: String) {
        launchSimpleCall { scoreRepository.addGame(gameName) }
    }

    fun onScoreIncremented(score: Score) {
        launchSimpleCall { scoreRepository.updateScore(score.scoreId, score.winCount + 1) }
    }

    fun onScoreDecremented(score: Score) {
        val winCount = score.winCount - 1

        if (winCount < 0) {
            Timber.d("Score is already 0. Not updating.")
            return
        }

        launchSimpleCall { scoreRepository.updateScore(score.scoreId, winCount) }
    }

    private fun launchSimpleCall(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (error: Exception) {
                _errorFlow.tryEmit(
                    ScoreManagementError(
                        error.localizedMessage ?: "Unable to update Score.", error
                    )
                )
            }
        }
    }

}

class ScoreManagementError(message: String, cause: Throwable?) : Throwable(message, cause)
