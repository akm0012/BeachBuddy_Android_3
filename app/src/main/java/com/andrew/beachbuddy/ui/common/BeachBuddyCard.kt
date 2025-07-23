package com.andrew.beachbuddy.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.andrew.beachbuddy.R

@Composable
fun BeachBuddyCard(
    modifier: Modifier = Modifier,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = colorResource(R.color.card_background_color)
    ),
    padding: Dp = 5.dp,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit
) =
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = colors,
        shape = MaterialTheme.shapes.extraSmall,
        onClick = onClick,
        modifier = modifier.padding(padding)
    ) {
        content()
    }
