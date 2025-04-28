package com.andrew.beachbuddy.ui.common

import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.andrew.beachbuddy.R

@Composable
fun BeachBuddyCard(
    modifier: Modifier = Modifier,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = colorResource(R.color.card_background_color)
    ),
    content: @Composable () -> Unit
) =
    Card(
        colors = colors,
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
    ) {
        content()
    }
