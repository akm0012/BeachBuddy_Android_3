package com.andrew.beachbuddy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andrew.beachbuddy.database.model.RequestedItem
import com.andrew.beachbuddy.ui.screens.DashboardScreen
import com.andrew.beachbuddy.ui.screens.RequestedItemsScreen
import com.andrew.beachbuddy.ui.theme.BeachBuddyTheme
import com.andrew.beachbuddy.ui.theme.backgroundDark
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemState
import com.andrew.beachbuddy.ui.viewmodels.RequestedItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeachBuddyTheme {


//                Scaffold(modifier = Modifier.fillMaxSize()) {

                Column {
                    Spacer(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.error)
                            .height(50.dp)
                            .fillMaxWidth()
                    )

//                                .padding(top = 50.dp))

                    DashboardScreen()
                }

//                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeachBuddyTheme {
        Greeting("Android")
    }
}