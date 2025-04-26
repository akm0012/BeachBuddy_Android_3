package com.andrew.beachbuddy.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun ProfilePhoto(
    @DrawableRes imageResourceId: Int = 0,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(imageResourceId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(CircleShape)
    )

}

@Preview(name = "Large Profile Photo", showBackground = true)
@Composable
private fun ProfilePhotoPreview() {
    BeachBuddyTheme {
//        ProfilePhoto(Modifier.size(79.dp))
    }
}