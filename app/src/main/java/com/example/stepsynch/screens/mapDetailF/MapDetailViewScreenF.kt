package com.example.stepsynch.screens.mapDetailF

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
import com.example.stepsynch.screens.mapDetailF.LandmarkDetailDialogF
import com.example.stepsynch.screens.mapDetailF.LandmarkNodeF
import com.example.stepsynch.screens.mapDetailF.MapDetailBottomStatsF
import com.example.stepsynch.screens.mapDetailF.MapDetailHeaderF

@Composable
fun MapDetailViewScreenF (
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
            Landmark(1, "Fallen Log Crossing", false, 0.20f, 0.30f),
            Landmark(2, "Fern Hollow", false, 0.50f, 0.50f),
            Landmark(3, "Forest Stream", false, 0.70f, 0.25f),
            Landmark(4, "Mossy Clearing", false, 0.35f, 0.60f),
            Landmark(5, "Owls Lookout", false, 0.75f, 0.65f),
            Landmark(6, "Sunbeam Glade", false, 0.15f, 0.75f),
            Landmark(7, "Whispering Pines", false, 0.50f, 0.15f),
            Landmark(8, "Wildflower Patch", false, 0.65f, 0.80f),
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

        MapDetailHeaderF(
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
                LandmarkNodeF(
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

        MapDetailBottomStatsF(
            collected = collectedItems,
            total = totalItems
        )
    }

    if (dialogOpen) {
        LandmarkDetailDialogF(
            landmark = selectedLandmark,
            onDismiss = { dialogOpen = false },
            onCollect = {
                collectLandmark()
                dialogOpen = false
            }
        )
    }
}