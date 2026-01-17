package com.example.stepsynch.screens.mapDetail

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

@Composable
fun MapDetailViewScreen(
    navController: NavController, regionId: Int, authRepository: AuthRepository
) {
    val currentUser by authRepository.currentUser.collectAsState()
    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
    val collectedLandmarkIds = remember { mutableStateListOf<Int>() }
    var energy by remember { mutableStateOf(0) }

    LaunchedEffect(currentUser, regionId) {
        currentUser?.uid?.let { uid ->
            authRepository.getUserGameStats(uid) { userGameStats ->
                gameStats = userGameStats
            }
            authRepository.getCollectedLandmarks(regionId, uid) {
                collectedLandmarkIds.clear()
                collectedLandmarkIds.addAll(it)
            }
        }
    }

    LaunchedEffect(gameStats) {
        energy = gameStats?.energyPoints ?: 0
    }

    //var energy = gameStats?.energyPoints ?: 0
    //var energy by remember { mutableStateOf(2450) }
    var explorationProgress by remember { mutableStateOf(65) }
    //var collectedItems by remember { mutableStateOf(0) }
    val collectedItems = collectedLandmarkIds.size
    val totalItems = 8

    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    var dialogOpen by remember { mutableStateOf(false) }

    val landmarks = remember {
        mutableStateListOf(
            Landmark(1, "Oak Tree", false, 0.20f, 0.30f),
            Landmark(2, "Stone Bridge", false, 0.50f, 0.40f),
            Landmark(3, "Lookout Point", false, 0.70f, 0.25f),
            Landmark(4, "Hidden Cave", false, 0.35f, 0.60f),
            Landmark(5, "Waterfall", false, 0.80f, 0.70f),
            Landmark(6, "Ancient Oak", false, 0.15f, 0.75f),
            Landmark(7, "Summit", false, 0.50f, 0.15f),
            Landmark(8, "Crystal Lake", false, 0.65f, 0.80f),
        )
    }

    LaunchedEffect(collectedLandmarkIds.toList()) {
        landmarks.replaceAll {
            it.copy(collected = collectedLandmarkIds.contains(it.id))
        }
    }

    fun completeRegion(userUid: String) {
        authRepository.markRegionCompleted(regionId, userUid)

        authRepository.updateEnergy(
            userUid = userUid,
            deltaEnergy = 250,
            deltaTotalEnergy = 250
        )
    }

    fun collectLandmark() {
        val lm = selectedLandmark ?: return
        val uid = currentUser?.uid ?: return
        if (lm.collected) return

        //Update UI instantly
        landmarks.replaceAll {
            if (it.id == lm.id) it.copy(collected = true) else it
        }

        //Subtract energy
        energy -= 100
        authRepository.updateEnergy(uid, deltaEnergy = -100)

        //Insert into added_landmark
        authRepository.addLandmarkForUser(
            regionId = regionId,
            landmarkId = lm.id,
            userUid = uid
        )

        //Re-check region completion
        authRepository.getCollectedLandmarks(regionId, uid) { collected ->
            if (collected.size == 8) {
                completeRegion(uid)
            }
        }
        collectedLandmarkIds.add(lm.id)
        authRepository.getCollectedLandmarks(regionId, uid) {
            collectedLandmarkIds.clear()
            collectedLandmarkIds.addAll(it)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFA8CCA8), // light green base
                        Color(0xFF4A6B4A)  // slightly richer green
                    )
                )
            )
    ) {

        MapDetailHeader(
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
                LandmarkNode(
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
            PlayerIndicator(
                x = 6.35f,
                y = 0.60f,
                width = constraints.maxWidth,
                height = constraints.maxHeight
            )
        }

        MapDetailBottomStats(
            collected = collectedItems,
            total = totalItems
        )
    }

    if (dialogOpen) {
        LandmarkDetailDialog(
            landmark = selectedLandmark,
            onDismiss = { dialogOpen = false },
            onCollect = {
                collectLandmark()
                dialogOpen = false
            }
        )
    }
}
