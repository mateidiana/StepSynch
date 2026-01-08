package com.example.stepsynch.screens.mapDetailS

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.stepsynch.models.Landmark
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import kotlin.math.min
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.repository.AuthRepository
import com.example.stepsynch.screens.mapDetailS.LandmarkDetailDialogS
import com.example.stepsynch.screens.mapDetailS.LandmarkNodeS
import com.example.stepsynch.screens.mapDetailS.MapDetailBottomStatsS
import com.example.stepsynch.screens.mapDetailS.MapDetailHeaderS

@Composable
fun MapDetailViewScreenS (
    navController: NavController, regionId: Int, authRepository: AuthRepository
) {
    val currentUser by authRepository.currentUser.collectAsState()
    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let { uid ->
            authRepository.getUserGameStats(uid) { userGameStats ->
                gameStats = userGameStats
            }
        }
    }

    var energy = gameStats?.energyPoints ?: 0
    //var energy by remember { mutableStateOf(2450) }
    var explorationProgress by remember { mutableStateOf(65) }
    var collectedItems by remember { mutableStateOf(0) }
    val totalItems = 8

    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    var dialogOpen by remember { mutableStateOf(false) }

    val landmarks = remember {
        mutableStateListOf(
            Landmark(1, "Celestial Path", false, 0.20f, 0.30f),
            Landmark(2, "Cloud Arbor", false, 0.50f, 0.50f),
            Landmark(3, "Floating Terrace", false, 0.70f, 0.25f),
            Landmark(4, "Hanging Waterfall", false, 0.30f, 0.60f),
            Landmark(5, "Sky Orchard", false, 0.75f, 0.65f),
            Landmark(6, "Starflower Meadow", false, 0.15f, 0.75f),
            Landmark(7, "Sunlit Conservatory", false, 0.50f, 0.15f),
            Landmark(8, "Wind Chime Grove", false, 0.65f, 0.80f),
        )
    }

    fun collectLandmark() {
        selectedLandmark?.let { lm ->
            val index = landmarks.indexOfFirst { it.id == lm.id }
            if (index != -1 && !lm.collected) {
                landmarks[index] = lm.copy(collected = true)
                collectedItems++
                explorationProgress = min(100, explorationProgress + 10)
                energy -= 125
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFA8CCA8), // light brown base
                        Color(0xFF4A6B4A)  // slightly richer brown
                    )
                )
            )
    ) {

        MapDetailHeaderS(
            progress = explorationProgress,
            energy = energy,
            onBack = { navController.navigateUp() }
        )

        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            // Background terrain
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color(0x4482A864),
                                Color.Transparent
                            )
                        )
                    )
            )

            landmarks.forEach { landmark ->
                LandmarkNodeS(
                    landmark = landmark,
                    width = constraints.maxWidth,
                    height = constraints.maxHeight,
                    onClick = {
                        selectedLandmark = landmark
                        dialogOpen = true
                    }
                )
            }

            // Player indicator
//            PlayerIndicator(
//                x = 6.35f,
//                y = 0.60f,
//                width = constraints.maxWidth,
//                height = constraints.maxHeight
//            )
        }

        MapDetailBottomStatsS(
            collected = collectedItems,
            total = totalItems
        )
    }

    if (dialogOpen) {
        LandmarkDetailDialogS(
            landmark = selectedLandmark,
            onDismiss = { dialogOpen = false },
            onCollect = {
                collectLandmark()
                dialogOpen = false
            }
        )
    }
}