package com.andrew.beachbuddy.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun DividerLine(modifier: Modifier = Modifier) {

    val density = LocalDensity.current
    val onePixel = with(density) { 1f.toDp() }

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(onePixel)
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
    )

}

@Preview(showBackground = true)
@Composable
private fun DividerLinePreview() {
    BeachBuddyTheme {
        Column {
            Spacer(Modifier.height(50.dp))
            DividerLine(modifier = Modifier.padding(start = 20.dp))
            Spacer(Modifier.height(50.dp))
        }
    }
}