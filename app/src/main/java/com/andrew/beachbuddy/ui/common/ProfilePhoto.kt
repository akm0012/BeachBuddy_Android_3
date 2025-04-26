package com.andrew.beachbuddy.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.andrew.beachbuddy.BuildConfig
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun ProfilePhoto(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .placeholder(R.drawable.baseline_person_24)
            .scale(Scale.FILL)
            .addHeader("AppToken", BuildConfig.APP_SECRET_HEADER)
            .build(),
        contentDescription = null,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Preview(name = "Large Profile Photo", showBackground = true)
@Composable
private fun ProfilePhotoPreview() {
    BeachBuddyTheme {
        ProfilePhoto(
            imageUrl = "",
            modifier = Modifier.size(79.dp)
        )
    }
}