package com.andrew.beachbuddy.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.domainmodels.RequestedItemsDM
import com.andrew.beachbuddy.ui.specific.requesteditem.CompletedTodayDivider
import com.andrew.beachbuddy.ui.specific.requesteditem.RequestedItemComposable
import com.andrew.beachbuddy.ui.specific.requesteditem.RequestedItemUiState
import com.andrew.beachbuddy.ui.specific.requesteditem.RequestedItemsEmptyState
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemState
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemViewModel

@Composable
fun RequestedItemsScreen(
    modifier: Modifier = Modifier,
    requestedItemViewModel: RequestedItemViewModel = viewModel() // todo: make this Hilt View Model
) {
    val uiState by requestedItemViewModel.uiState.collectAsStateWithLifecycle()

    RequestedItemsScreen(
        uiState = uiState,
        onItemMarkedAsComplete = requestedItemViewModel::onRequestedItemChecked,
        onPullToRefresh = requestedItemViewModel::onPullToRefresh,
        modifier = modifier
    )
}

@Composable
fun RequestedItemsScreen(
    uiState: RequestedItemState,
    onItemMarkedAsComplete: (RequestedItem) -> Unit,
    onPullToRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nonCompletedItems = uiState.requestedItemsDomainModel.nonCompletedItems
    val completedItems = uiState.requestedItemsDomainModel.completedItems

    LazyColumn(
        modifier = modifier.padding(top = 8.dp)
    ) {
        val requestedItemsModifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)

        // Show top empty state if all items are completed
        item {
            AnimatedVisibility(nonCompletedItems.isEmpty()) {
                RequestedItemsEmptyState(modifier = Modifier.padding(top = 50.dp))
            }
        }

        // Show the non completed Items
        items(items = nonCompletedItems, key = { it.id }) { item: RequestedItem ->
            val requestedItemUiState = RequestedItemUiState(item)

            var isChecked by rememberSaveable { mutableStateOf(false) }

            RequestedItemComposable(
                titleText = requestedItemUiState.titleText,
                subtitleText = requestedItemUiState.subTitleText,
                profilePhotoUrl = requestedItemUiState.profilePhotoUrl,
                isCompleted = requestedItemUiState.isComplete,
                isChecked = isChecked,
                onCheckChanged = { isChecked = true },
                modifier = requestedItemsModifier
            )
        }

        // Show the completed items divider if there are any completed items
        item {
            AnimatedVisibility(completedItems.isNotEmpty()) {
                CompletedTodayDivider(modifier = Modifier.padding(top = 50.dp, bottom = 20.dp))
            }
        }

        // Show the completed items
        items(items = completedItems, key = { it.id }) { item: RequestedItem ->
            val requestedItemUiState = RequestedItemUiState(item)
            RequestedItemComposable(
                titleText = requestedItemUiState.titleText,
                subtitleText = requestedItemUiState.subTitleText,
                profilePhotoUrl = requestedItemUiState.profilePhotoUrl,
                isCompleted = requestedItemUiState.isComplete,
                isChecked = true,
                onCheckChanged = { /* Disabled when completed */ },
                modifier = requestedItemsModifier
            )
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedScreenPreview() {
    BeachBuddyTheme {
        RequestedItemsScreen(
            uiState = RequestedItemState(
                requestedItemsDomainModel = RequestedItemsDM(
                    nonCompletedItems = sampleNonCompletedItems,
                    completedItems = sampleCompletedItems
                ),
                isLoading = false,
                errorMessage = null
            ),
            onPullToRefresh = {},
            onItemMarkedAsComplete = { }
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedScreenPreviewAllCompleted() {
    BeachBuddyTheme {
        RequestedItemsScreen(
            uiState = RequestedItemState(
                requestedItemsDomainModel = RequestedItemsDM(
                    nonCompletedItems = emptyList(),
                    completedItems = sampleCompletedItems
                ),
                isLoading = false,
                errorMessage = null
            ),
            onPullToRefresh = {},
            onItemMarkedAsComplete = { }
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedScreenPreviewEmptyState() {
    BeachBuddyTheme {
        RequestedItemsScreen(
            uiState = RequestedItemState(
                requestedItemsDomainModel = RequestedItemsDM(
                    nonCompletedItems = emptyList(),
                    completedItems = emptyList()
                ),
                isLoading = false,
                errorMessage = null
            ),
            onPullToRefresh = {},
            onItemMarkedAsComplete = { }
        )
    }
}

val sampleCompletedItems = listOf(
    RequestedItem(
        id = "2",
        name = "LaCroix",
        requestorFirstName = "Andrew",
        isComplete = true,
        createdAtTime = 1745696496895L
    ),
    RequestedItem(
        id = "3",
        name = "PB&J",
        requestorFirstName = "Andrew",
        count = 6,
        isComplete = true,
        createdAtTime = 1745696496895L
    )
)

val sampleNonCompletedItems = listOf(
    RequestedItem(
        id = "4",
        name = "Beer",
        count = 12,
        requestorFirstName = "Andrew",
        isComplete = false,
        createdAtTime = 1745696496895L
    ),
    RequestedItem(
        id = "5",
        name = "Oreos and Hummus",
        requestorFirstName = "Andrew",
        count = 100,
        isComplete = false,
        createdAtTime = 1745696496895L
    )
)