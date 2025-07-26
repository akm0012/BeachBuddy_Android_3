package com.andrew.beachbuddy.ui.specific.uvindex

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.database.model.User
import com.andrew.beachbuddy.inject.BASE_ENDPOINT
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.BeachBuddyCard
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.domainmodels.WeatherDM
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme

@Composable
fun TimeToBurnProfileTile(
    weatherDM: WeatherDM,
    user: User,
    onTileClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentUVUiState = CurrentUVUiState(weatherDM)
    val cardContainerColor = colorResource(currentUVUiState.getUvColorForTimeToBurn(user.skinType))

    TimeToBurnProfileTile(
        profilePhotoUrl = "$BASE_ENDPOINT${user.photoUrl}",
        cardContainerColor = cardContainerColor,
        timeToBurnText = currentUVUiState.getTimeToBurn(user.skinType),
        onTileClicked = onTileClicked,
        modifier = modifier
    )
}

@Composable
fun TimeToBurnProfileTile(
    profilePhotoUrl: String,
    cardContainerColor: Color,
    timeToBurnText: String?,
    modifier: Modifier = Modifier,
    onTileClicked: () -> Unit = { },
) {
    TimeToBurnCard(
        baseColor = cardContainerColor,
        onClick = onTileClicked,
        padding = 0.dp,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            ProfilePhoto(
                imageUrl = profilePhotoUrl,
                modifier = Modifier.size(30.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val textModifier = Modifier.padding(start = 4.dp)

                // Invisible place holder to largest text we'd expect
                Text(
                    text = "XXm",
                    maxLines = 1,
                    modifier = textModifier.then(Modifier.alpha(0f))
                )

                val isContainerWhite = cardContainerColor.toArgb() == Color.White.toArgb()

                Text(
                    text = timeToBurnText ?: CAN_NOT_BURN_MESSAGE,
                    maxLines = 1,
                    color = if (isContainerWhite) colorResource(R.color.text_gray) else colorResource(
                        R.color.white
                    ),
                    modifier = textModifier
                )
            }
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun TimeToBurnProfileTilePreview() {
    BeachBuddyTheme {
        Column {
            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_low),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_low),
                timeToBurnText = null
            )
        }
    }

}

@DarkLightPhonePreviews
@Composable
private fun TimeToBurnProfileTilePreviewAllColors() {
    BeachBuddyTheme {
        Column {
            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_extreme),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_very_high),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_high),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_moderate),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_low),
                timeToBurnText = "1h 55m"
            )

            TimeToBurnProfileTile(
                profilePhotoUrl = "",
                cardContainerColor = colorResource(R.color.uv_index_view_background),
                timeToBurnText = "1h 55m"
            )
        }
    }

}

@Composable
fun TimeToBurnCard(
    modifier: Modifier = Modifier,
    baseColor: Color,
    padding: Dp = 5.dp,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            baseColor.copy(alpha = 0.1f),
            baseColor
        )
    )

    // If white background, just use the normal BeachBuddyCard
    if (baseColor.toArgb() == Color.White.toArgb()) {

        BeachBuddyCard(
            onClick = onClick,
            padding = 0.dp,
            modifier = modifier
        ) {
            content()
        }

    } else {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = MaterialTheme.shapes.extraSmall,
            onClick = onClick,
            modifier = modifier
                .padding(padding)
                .border(
                    width = 1.dp,
                    color = baseColor.copy(alpha = 0.4f), // or any suitable border color
                    shape = MaterialTheme.shapes.extraSmall
                )
        ) {

            // Gradient background using Box (Card containers can't have gradients)
            Box(
                modifier = Modifier
                    .background(brush = gradientBrush, shape = MaterialTheme.shapes.extraSmall)
            ) {
                content()
            }
        }
    }
}

