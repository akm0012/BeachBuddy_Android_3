package com.andrew.beachbuddy.ui.specific.managegames

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R
import com.andrew.beachbuddy.ui.DarkLightPhonePreviews
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.Dimens.StandardPadding

@Composable
fun EditScoreRow(
    gameName: String,
    score: Int,
    onIncrementClicked: () -> Unit,
    onDecrementClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = StandardPadding)
    ) {
        // Decrement Button
        Button(
            onClick = onDecrementClicked,
            modifier = Modifier
        ) {
            Text("-")
        }

        // Game Name
        Text(
            text = gameName,
            color = colorResource(R.color.dashboard_text_dark),
            fontSize = TextUnit(16f, TextUnitType.Sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(1f)
        )

        // Score
        Text(
            text = score.toString(),
            color = colorResource(R.color.colorAccent),
            fontSize = TextUnit(22f, TextUnitType.Sp),
            modifier = Modifier.padding(end = 10.dp)
        )

        // Increment Button
        Button(
            onClick = onIncrementClicked,
            modifier = Modifier
        ) {
            Text("+")
        }
    }
}

@DarkLightPhonePreviews
@Composable
private fun EditScoreRowPreview() {
    BeachBuddyTheme {
        Column {
            EditScoreRow(
                gameName = "Mario Kart",
                score = 5,
                onIncrementClicked = {},
                onDecrementClicked = {}
            )

            EditScoreRow(
                gameName = "A Very Long Game Name That Keeps Going!",
                score = 15,
                onIncrementClicked = {},
                onDecrementClicked = {}
            )
        }
    }
}
