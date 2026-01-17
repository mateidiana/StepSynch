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
import com.example.stepsynch.screens.mapDetailS.MapDetailViewScreenS
import com.example.stepsynch.screens.mapDetailA.MapDetailViewScreenA
import com.example.stepsynch.screens.mapDetailP.MapDetailViewScreenP
import com.example.stepsynch.screens.mapDetailW.MapDetailViewScreenW
import com.example.stepsynch.ui.theme.StepSynchTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.FirebaseApp
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.setContent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field



class MainActivity : ComponentActivity() {

    private val GOOGLE_FIT_REQUEST = 1001

    private val fitnessOptions = FitnessOptions.builder()
        .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)

        setContent {
            StepSynchTheme {
                AppNavigation(
                    onRequestGoogleFit = { requestGoogleFit() }
                )
            }
        }
    }

    private fun requestGoogleFit() {
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                this,
                GOOGLE_FIT_REQUEST,
                account,
                fitnessOptions
            )
        } else {
            readSteps()
        }
    }

    private fun readSteps() {
        val account = GoogleSignIn.getLastSignedInAccount(this) ?: return

        Fitness.getHistoryClient(this, account)
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener { dataSet ->
                val steps =
                    if (dataSet.isEmpty) 0
                    else dataSet.dataPoints[0]
                        .getValue(Field.FIELD_STEPS)
                        .asInt()

                // TODO: save steps to ViewModel / Firebase
                println("STEPS TODAY = $steps")
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_FIT_REQUEST && resultCode == Activity.RESULT_OK) {
            readSteps()
        }
    }
}

@Composable
fun AppNavigation(onRequestGoogleFit: () -> Unit) {
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
            ChallengesScreen(navController, authRepository)
        }
        composable("profile") {
            ProfileScreen(navController, authRepository)
        }
        composable("friends") {
            FriendsScreen(navController, authRepository)
        }
        composable("leaderboard") {
            LeaderboardScreen(navController, authRepository)
        }
        composable("map") {
            MapExplorationScreen(navController, authRepository)
        }
        composable(
            route = "mapDetail/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreen(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailD/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenD(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailM/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenM(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailC/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenC(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailF/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenF(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailH/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenH(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailS/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenS(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailA/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenA(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailP/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenP(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
        composable(
            route = "mapDetailW/{regionId}",
            arguments = listOf(navArgument("regionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val regionId = backStackEntry.arguments?.getInt("regionId")!!
            MapDetailViewScreenW(
                navController = navController,
                regionId = regionId,
                authRepository
            )
        }
    }
}
