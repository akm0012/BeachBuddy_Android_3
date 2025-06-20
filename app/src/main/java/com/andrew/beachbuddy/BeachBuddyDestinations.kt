package com.andrew.beachbuddy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

interface BeachBuddyDestination {
    val icon: ImageVector
    val route: String
    val label: String
    val contentDestination: String
}

object Dashboard : BeachBuddyDestination {
    override val icon: ImageVector = Icons.Filled.Home
    override val route: String = "dashboard"
    override val label: String = "Dashboard"
    override val contentDestination: String = "Dashboard"
}

object BeachList : BeachBuddyDestination {
    override val icon: ImageVector = Icons.AutoMirrored.Filled.List
    override val route: String = "beachlist"
    override val label: String = "Beach List"
    override val contentDestination: String = "Beach List"
}

object ConfigureGames : BeachBuddyDestination {
    override val icon: ImageVector = Icons.Filled.Settings
    override val route: String = "ConfigureGames"
    override val label: String = "Configure Games"
    override val contentDestination: String = "Configure Games"
}

// Screens to be shown in the Bottom Bar
val topLevelDestinations = listOf(Dashboard, BeachList)