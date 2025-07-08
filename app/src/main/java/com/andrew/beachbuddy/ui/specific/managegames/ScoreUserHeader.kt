package com.andrew.beachbuddy.ui.specific.managegames

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun ScoreUserHeader(
    userFirstName: String,
    userProfilePhotoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ProfilePhoto(
            imageUrl = userProfilePhotoUrl,
            modifier = Modifier
                .size(79.dp)
        )

        Text(
            text = userFirstName,
            fontSize = TextUnit(22f, TextUnitType.Sp),
            color = colorResource(R.color.dashboard_text_dark),
            modifier = Modifier.padding(start = StandardPadding)
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun ScoreUserHeaderPreview() {
    BeachBuddyTheme {
        ScoreUserHeader(
            userFirstName = "Andrew",
            userProfilePhotoUrl = ""
        )
    }
}