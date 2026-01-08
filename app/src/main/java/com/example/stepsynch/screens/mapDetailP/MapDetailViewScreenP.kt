package com.example.stepsynch.screens.mapDetailP

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
import com.example.stepsynch.screens.mapDetailP.LandmarkDetailDialogP
import com.example.stepsynch.screens.mapDetailP.LandmarkNodeP
import com.example.stepsynch.screens.mapDetailP.MapDetailBottomStatsP
import com.example.stepsynch.screens.mapDetailP.MapDetailHeaderP

@Composable
fun MapDetailViewScreenP (
    navController: NavController, regionId: Int
) {
    var energy by remember { mutableStateOf(2450) }
    var explorationProgress by remember { mutableStateOf(65) }
    var collectedItems by remember { mutableStateOf(0) }
    val totalItems = 8

    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    var dialogOpen by remember { mutableStateOf(false) }

    val landmarks = remember {
        mutableStateListOf(
            Landmark(1, "Endless Horizon", false, 0.20f, 0.30f),
            Landmark(2, "Glacier Shelf", false, 0.50f, 0.50f),
            Landmark(3, "High Camp", false, 0.70f, 0.25f),
            Landmark(4, "Icefall Cliffs", false, 0.35f, 0.60f),
            Landmark(5, "Knife Edge Pass", false, 0.75f, 0.65f),
            Landmark(6, "Summit Cairn", false, 0.15f, 0.75f),
            Landmark(7, "Summit Cross", false, 0.50f, 0.15f),
            Landmark(8, "Wind Carved Ridge", false, 0.65f, 0.80f),
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

        MapDetailHeaderP(
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
                LandmarkNodeP(
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

        MapDetailBottomStatsP(
            collected = collectedItems,
            total = totalItems
        )
    }

    if (dialogOpen) {
        LandmarkDetailDialogP(
            landmark = selectedLandmark,
            onDismiss = { dialogOpen = false },
            onCollect = {
                collectLandmark()
                dialogOpen = false
            }
        )
    }
}