package com.andrew.beachbuddy.ui.specific

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.ui.DarkLightPreviews
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.ProfileCircleSize
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun RequestedItem(
    isChecked: Boolean,
    titleText: String,
    subtitleText: String,
    profilePhotoUrl: String,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckChanged,
                Modifier.padding(vertical = 20.dp, horizontal = StandardPadding)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(end = StandardPadding)
                    .weight(1f)
            ) {
                // Todo: style text
                Text(
                    text = titleText
                )
                Text(
                    text = subtitleText
                )
            }

            ProfilePhoto(
                imageUrl = profilePhotoUrl,
                modifier = Modifier
                    .size(ProfileCircleSize)
            )

            Spacer(modifier = Modifier.size(StandardPadding))
        }
    }


}

@DarkLightPreviews
@Composable
private fun RequestedItemPreview() {
    BeachBuddyTheme {
        RequestedItem(
            isChecked = false,
            titleText = "Beer (13)",
            subtitleText = "Andrew • Monday 3:33pm",
            profilePhotoUrl = "",
            onCheckChanged = {}
        )
    }
}