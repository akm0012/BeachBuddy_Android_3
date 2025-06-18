package com.andrew.beachbuddy.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.layout.ContentScale
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
    modifier: Modifier = Modifier,
    isGrayedOut: Boolean = false
) {
    // Animate saturation between 1f (full color) and 0f (grayscale)
    val saturation by animateFloatAsState(
        targetValue = if (isGrayedOut) 0f else 1f,
        animationSpec = tween(1000),
        label = "saturationAnimation",
    )

    // Set the saturation in the ColorMatrix
    val colorMatrix = ColorMatrix().apply { setToSaturation(saturation) }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .placeholder(R.drawable.baseline_person_24)
            .scale(Scale.FILL)
            .addHeader("AppToken", BuildConfig.APP_SECRET_HEADER)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        colorFilter = ColorFilter.colorMatrix(colorMatrix),
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
    )
}

@Preview(name = "Large Profile Photo", showBackground = true, apiLevel = 30)
@Composable
private fun ProfilePhotoPreview() {
    BeachBuddyTheme {
        ProfilePhoto(
            imageUrl = "",
            modifier = Modifier.size(79.dp)
        )
    }
}

@Preview(name = "Large Profile Photo - Dynamic Color", showBackground = true)
@Composable
private fun ProfilePhotoPreviewNewApi() {
    BeachBuddyTheme {
        ProfilePhoto(
            imageUrl = "",
            modifier = Modifier.size(79.dp)
        )
    }
}