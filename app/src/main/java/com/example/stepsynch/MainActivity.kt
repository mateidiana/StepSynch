package com.example.stepsynch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stepsynch.screens.SignInScreen
import com.example.stepsynch.screens.WelcomeScreen
import com.example.stepsynch.ui.theme.StepSynchTheme

import androidx.activity.compose.setContent
import androidx.compose.material3.Surface

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stepsynch.screens.SignInScreen
import com.example.stepsynch.screens.SignUpScreen
import com.example.stepsynch.screens.WelcomeScreen
import com.example.stepsynch.ui.theme.StepSynchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StepSynchTheme {
                AppNavigation()

            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            // Pass a lambda to handle navigation when button is clicked
            WelcomeScreen(
                navController = navController
            )
        }
        composable("signin") {
            SignInScreen(navController = navController)
        }
        composable("signup") {
            SignUpScreen(navController = navController)
        }
    }

}
