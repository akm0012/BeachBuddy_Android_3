package com.andrew.beachbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andrew.beachbuddy.extensions.toast
import com.andrew.beachbuddy.ui.screens.DashboardScreen
import com.andrew.beachbuddy.ui.screens.RequestedItemsScreen
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeachBuddyApp()
        }
    }
}


@Composable
fun BeachBuddyApp() {
    BeachBuddyTheme {
        val navController = rememberNavController()
        val startDestination = Dashboard
        var selectedDestinationRoute by rememberSaveable {
            mutableStateOf(startDestination.route)
        }

        Scaffold(
            bottomBar = {
                NavigationBar(
                    windowInsets = NavigationBarDefaults.windowInsets
                ) {
                    topLevelDestinations.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = selectedDestinationRoute == destination.route,
                            onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestinationRoute = destination.route
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDestination
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            val context = LocalContext.current

            NavHost(
                navController = navController,
                startDestination = Dashboard.route,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = Dashboard.route) {
                    DashboardScreen(
                        onNightModeClicked = { "Night mode clicked".toast(context) },
                        onSettingsClicked = { navController.navigate(route = ConfigureGames.route) }
                    )
                }

                composable(route = BeachList.route) {
                    RequestedItemsScreen()
                }

                composable(route = ConfigureGames.route) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Configure Games TODO!")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    BeachBuddyTheme {
        BeachBuddyApp()
    }
}