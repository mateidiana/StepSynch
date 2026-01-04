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
import androidx.compose.runtime.remember

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stepsynch.repository.AuthRepository
import com.example.stepsynch.screens.SignInScreen
import com.example.stepsynch.screens.SignUpScreen
import com.example.stepsynch.screens.OnboardScreen
import com.example.stepsynch.screens.HomeScreen
import com.example.stepsynch.screens.ChallengesScreen
import com.example.stepsynch.screens.ProfileScreen
import com.example.stepsynch.screens.FriendsScreen
import com.example.stepsynch.screens.LeaderboardScreen
import com.example.stepsynch.screens.WelcomeScreen
import com.example.stepsynch.screens.map.MapExplorationScreen
import com.example.stepsynch.screens.mapDetail.MapDetailViewScreen
import com.example.stepsynch.screens.mapDetailD.MapDetailViewScreenD
import com.example.stepsynch.screens.mapDetailM.MapDetailViewScreenM
import com.example.stepsynch.screens.mapDetailC.MapDetailViewScreenC
import com.example.stepsynch.screens.mapDetailF.MapDetailViewScreenF
import com.example.stepsynch.screens.mapDetailH.MapDetailViewScreenH
import com.example.stepsynch.ui.theme.StepSynchTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
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
    val authRepository = remember { AuthRepository() }
    NavHost(navController = navController, startDestination = "welcome") {

        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("signin") {
            SignInScreen(navController = navController, authRepository)
        }
        composable("signup") {
            SignUpScreen(navController = navController, authRepository)
        }
        composable("onboarding") {
            OnboardScreen(navController, authRepository)
        }
        composable("home") {
            HomeScreen(navController, authRepository)
        }
        composable("challenges") {
            ChallengesScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController, authRepository)
        }
        composable("friends") {
            FriendsScreen(navController)
        }
        composable("leaderboard") {
            LeaderboardScreen(navController)
        }
        composable("map") {
            MapExplorationScreen(navController)
        }
        composable(
            route = "mapDetail/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreen(
                navController = navController,
                regionId = regionId
            )
        }
        composable(
            route = "mapDetailD/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenD(
                navController = navController,
                regionId = regionId
            )
        }
        composable(
            route = "mapDetailM/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenM(
                navController = navController,
                regionId = regionId
            )
        }
        composable(
            route = "mapDetailC/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenC(
                navController = navController,
                regionId = regionId
            )
        }
        composable(
            route = "mapDetailF/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenF(
                navController = navController,
                regionId = regionId
            )
        }
        composable(
            route = "mapDetailH/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenH(
                navController = navController,
                regionId = regionId
            )
        }
    }
}
