package com.andrew.beachbuddy.ui.specific.requesteditem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.common.ProfilePhoto
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.ProfileCircleSize
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

/**
 * This represents a Requested Beach Item.
 *
 * It may be in 1 of 3 states:
 *
 * 1. Unchecked
 * 2. Checked, but waiting to be saved, processed, and completed.
 *  - This will look just like the Unchecked, but with the Checkbox checked.
 * 3. Completed
 *  - This is when the item is in the completed section, and therefore grayed out and stricked through
 */
@Composable
fun RequestedItem(
    titleText: String,
    subtitleText: String,
    profilePhotoUrl: String,
    isChecked: Boolean,
    isCompleted: Boolean,
    onCheckChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckChanged,
                Modifier.padding(vertical = 20.dp, horizontal = StandardPadding)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(end = StandardPadding, top = 10.dp, bottom = 10.dp)
                    .weight(1f)
            ) {
                // The strike through 
                val strikeThroughDecoration = if (isCompleted) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }

                Text(
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        textDecoration = strikeThroughDecoration
                    ),
                    text = titleText,
                )
                Text(
                    style = MaterialTheme.typography.labelSmall.copy(
                        textDecoration = strikeThroughDecoration
                    ),
                    text = subtitleText
                )
            }

            ProfilePhoto(
                imageUrl = profilePhotoUrl,
                isGrayedOut = isCompleted,
                modifier = Modifier
                    .size(ProfileCircleSize)
            )

            Spacer(modifier = Modifier.size(StandardPadding))
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedItemPreview() {
    BeachBuddyTheme {
        RequestedItem(
            isChecked = false,
            isCompleted = false,
            titleText = "Beer (13)",
            subtitleText = "Andrew • Monday 3:33pm",
            profilePhotoUrl = "",
            onCheckChanged = {}
        )
    }
}

@Preview
@Composable
private fun RequestedItemPreviewChecked() {
    BeachBuddyTheme {
        RequestedItem(
            isChecked = true,
            isCompleted = false,
            titleText = "Beer (13)",
            subtitleText = "Andrew • Monday 3:33pm",
            profilePhotoUrl = "",
            onCheckChanged = {}
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedItemPreviewCompleted() {
    BeachBuddyTheme {
        RequestedItem(
            isChecked = true,
            isCompleted = true,
            titleText = "Beer (13)",
            subtitleText = "Andrew • Monday 3:33pm",
            profilePhotoUrl = "",
            onCheckChanged = {}
        )
    }
}

@DarkLightPhonePreviews
@Composable
private fun RequestedItemPreviewLongerText() {
    BeachBuddyTheme {
        RequestedItem(
            isChecked = false,
            isCompleted = false,
            titleText = "This is a long message that would probably not ever happen! (13)",
            subtitleText = "Andrew • Monday 3:33pm",
            profilePhotoUrl = "",
            onCheckChanged = {}
        )
    }
}