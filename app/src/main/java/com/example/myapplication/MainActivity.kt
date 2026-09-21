package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.BasicInfoScreen
import com.example.myapplication.ui.screen.HubungiKamiScreen
import com.example.myapplication.ui.theme.JualanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = BASIC_INFO_ROUTE
                    ) {
                        composable(BASIC_INFO_ROUTE) {
                            BasicInfoScreen(
                                onNavigateToContact = {
                                    navController.navigate(CONTACT_FORM_ROUTE)
                                }
                            )
                        }
                        composable(CONTACT_FORM_ROUTE) {
                            HubungiKamiScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    private companion object {
        const val BASIC_INFO_ROUTE = "basic_info"
        const val CONTACT_FORM_ROUTE = "form_screen"
    }
}
