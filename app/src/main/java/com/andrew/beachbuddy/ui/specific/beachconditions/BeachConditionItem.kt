package com.andrew.beachbuddy.ui.specific.beachconditions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.DividerLine
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun BeachConditionsItem(
    beachConditionUiState: BeachConditionUiState,
    showBottomDivider: Boolean,
    modifier: Modifier = Modifier
) {

    BeachConditionsItem(
        image = beachConditionUiState.iconDrawable,
        titleText = beachConditionUiState.title,
        contentText = beachConditionUiState.content,
        showBottomDivider = showBottomDivider,
        modifier = modifier
    )
}

@Composable
fun BeachConditionsItem(
    @DrawableRes image: Int,
    titleText: String,
    contentText: String,
    showBottomDivider: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(start = StandardPadding)) {

        val contentAndTextColor = MaterialTheme.colorScheme.onSurface

        Image(
            painter = painterResource(image),
            contentDescription = null,
            colorFilter = ColorFilter.tint(contentAndTextColor),
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight()
        )

        // We wrap this Column in another Column in order to keep the content in the center and the
        // divider line at the bottom
        Column(
            modifier = Modifier
                .padding(start = StandardPadding)
                .fillMaxHeight()
        ) {
            // Centered Content
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = contentText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = contentAndTextColor
                )
            }

            if (showBottomDivider) {
                DividerLine()
            }
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun BeachConditionItemPreview() {

    BeachBuddyTheme {
        BeachConditionsItem(
            R.drawable.ic_clouds_100,
            titleText = "Cloud Coverage",
            contentText = "18%",
            showBottomDivider = true,
            modifier = Modifier.height(100.dp)
        )
    }

}