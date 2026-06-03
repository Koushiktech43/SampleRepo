package com.android.sampletestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.android.sampletestapplication.ui.theme.SampleTestApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTestApplicationTheme {
                FruitHubApp()
            }
        }
    }
}

enum class Screen {
    Splash, Welcome, Auth, Home, Basket, OrderDetails, Success
}

@Composable
fun FruitHubApp() {
    var currentScreen by remember { mutableStateOf(Screen.Splash) }
    var userName by remember { mutableStateOf("") }

    when (currentScreen) {
        Screen.Splash -> {
            SplashScreen(onTimeout = {
                currentScreen = Screen.Welcome
            })
        }
        Screen.Welcome -> {
            WelcomeScreen(onContinueClick = {
                currentScreen = Screen.Auth
            })
        }
        Screen.Auth -> {
            AuthScreen(onStartOrdering = { name ->
                userName = name
                currentScreen = Screen.Home
            })
        }
        Screen.Home -> {
            HomeScreen(onBasketClick = {
                currentScreen = Screen.Basket
            })
        }
        Screen.Basket -> {
            BasketScreen(onBackClick = {
                currentScreen = Screen.Home
            })
        }
        Screen.OrderDetails -> {
            OrderDetailsScreen(onBackClick = {
                currentScreen = Screen.Home
            })
        }
        Screen.Success -> {
            SuccessScreen(
                onTrackOrderClick = { /* Handle tracking */ },
                onContinueShoppingClick = { currentScreen = Screen.Home }
            )
        }
    }
}
