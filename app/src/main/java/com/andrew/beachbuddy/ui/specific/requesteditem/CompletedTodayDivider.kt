package com.andrew.beachbuddy.ui.specific.requesteditem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun CompletedTodayDivider(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        val color = MaterialTheme.colorScheme.onSurfaceVariant

        val spacerModifier = Modifier
            .background(color = color)
            .height(2.dp)
            .weight(1f)

        Spacer(Modifier.width(30.dp))
        Spacer(modifier = spacerModifier)

        Text(
            text = stringResource(R.string.completed_today).uppercase(),
            color = color,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .wrapContentWidth()
        )

        Spacer(modifier = spacerModifier)
        Spacer(Modifier.width(30.dp))
    }
}

@DarkLightPhonePreviews
@Composable
private fun CompletedTodayDividerPreview() {
    BeachBuddyTheme {
        CompletedTodayDivider()
    }
}