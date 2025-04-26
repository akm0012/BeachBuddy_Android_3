package com.andrew.beachbuddy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.repository.RequestedItemRepository
import com.andrew.beachbuddy.ui.domainmodels.RequestedItemsDM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RequestedItemState(
    val requestedItemsDomainModel: RequestedItemsDM = RequestedItemsDM(),
    val isLoading: Boolean = false, // todo this was originally only used to stop the PTR animation
    val errorMessage: String? = null
)

@HiltViewModel
class RequestedItemViewModel @Inject constructor(
    private val requestedItemRepository: RequestedItemRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RequestedItemState())
    val uiState: StateFlow<RequestedItemState> =
        _uiState
            .onStart {
                loadData()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = RequestedItemState()
            )

    private fun loadData() {
        viewModelScope.launch {
            requestedItemRepository.getRequestedItemsDomainModel().collect { requestedItems ->
                _uiState.update {
                    it.copy(
                        requestedItemsDomainModel = requestedItems
                    )
                }
            }
        }
    }

    fun onPullToRefresh() {
        viewModelScope.launch {
            try {
                requestedItemRepository.refreshRequestedItems()
            } catch (e: Exception) {
                showError(e.message)
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }

            }
        }
    }

    fun onRequestedItemChecked(requestedItem: RequestedItem) {
        viewModelScope.launch {
            try {
                requestedItemRepository.markRequestedItemAsComplete(requestedItem)
            } catch (e: Exception) {
                showError(e.message)
            }
        }
    }

    private fun showError(errorMessage: String?) {
        _uiState.update {
            it.copy(errorMessage = errorMessage)
        }
    }
}
