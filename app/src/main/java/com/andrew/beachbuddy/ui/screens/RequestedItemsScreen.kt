package com.andrew.beachbuddy.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.ui.DarkLightPreviews
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.domainmodels.RequestedItemsDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemState
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemViewModel
import timber.log.Timber

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

    

}


@DarkLightPreviews
@Preview (showBackground = true)
@Composable
private fun RequestedScreenPreview() {
    BeachBuddyTheme {
        RequestedItemsScreen(
            uiState = RequestedItemState(
                requestedItemsDomainModel = RequestedItemsDM(
                    nonCompletedItems = emptyList(),
                    completedItems = listOf(
                        RequestedItem(
                            name = "LaCroix",
                            count = 6,
                            isComplete = false
                        // todo: fill in more
                        )
                    )
                ),
                isLoading = false,
                errorMessage = null
            ),
            onPullToRefresh = {},
            onItemMarkedAsComplete = { }
        )
    }
}