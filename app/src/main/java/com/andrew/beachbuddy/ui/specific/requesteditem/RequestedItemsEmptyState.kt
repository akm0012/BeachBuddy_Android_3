package com.andrew.beachbuddy.ui.specific.requesteditem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun RequestedItemsEmptyState(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_beach_access_black_24dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
        )

        Text(
            text = stringResource(R.string.requested_items_empty_state_message),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = StandardPadding)
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedItemsEmptyStatePreview() {
    BeachBuddyTheme {
        RequestedItemsEmptyState()
    }
}