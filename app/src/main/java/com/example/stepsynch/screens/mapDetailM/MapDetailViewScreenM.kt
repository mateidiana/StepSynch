package com.example.stepsynch.screens.mapDetailM

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.stepsynch.models.Landmark
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.repository.AuthRepository
import kotlin.math.min

@Composable
fun MapDetailViewScreenM(
    navController: NavController,
    regionId: Int,
    authRepository: AuthRepository
) {
    val currentUser by authRepository.currentUser.collectAsState()

    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
    var energy by remember { mutableStateOf(0) }

    val collectedLandmarkIds = remember { mutableStateListOf<Int>() }

    // ─────────────────────────────────────────────
    // Load game stats + collected landmarks
    // ─────────────────────────────────────────────
    LaunchedEffect(currentUser, regionId) {
        currentUser?.uid?.let { uid ->
            authRepository.getUserGameStats(uid) {
                gameStats = it
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

    var explorationProgress by remember { mutableStateOf(65) }
    val collectedItems = collectedLandmarkIds.size
    val totalItems = 8

    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    var dialogOpen by remember { mutableStateOf(false) }

    // ─────────────────────────────────────────────
    // Landmark definitions
    // ─────────────────────────────────────────────
    val landmarks = remember {
        mutableStateListOf(
            Landmark(1, "Ridge Trail", false, 0.20f, 0.30f),
            Landmark(2, "Alpine Meadow", false, 0.50f, 0.40f),
            Landmark(3, "Boulder Field", false, 0.70f, 0.25f),
            Landmark(4, "Eagles Nest", false, 0.35f, 0.60f),
            Landmark(5, "Glacier Point", false, 0.75f, 0.65f),
            Landmark(6, "Mountain Lake", false, 0.15f, 0.75f),
            Landmark(7, "Pine Grove", false, 0.50f, 0.15f),
            Landmark(8, "Rocky Outcrop", false, 0.65f, 0.80f),
        )
    }

    LaunchedEffect(collectedLandmarkIds.toList()) {
        landmarks.replaceAll {
            it.copy(collected = collectedLandmarkIds.contains(it.id))
        }
    }

    // ─────────────────────────────────────────────
    // Region completion
    // ─────────────────────────────────────────────
    fun completeRegion(userUid: String) {
        authRepository.markRegionCompleted(regionId, userUid)
        authRepository.updateEnergy(
            userUid = userUid,
            deltaEnergy = 250,
            deltaTotalEnergy = 250
        )
    }

    // ─────────────────────────────────────────────
    // Collect landmark
    // ─────────────────────────────────────────────
    fun collectLandmark() {
        val lm = selectedLandmark ?: return
        val uid = currentUser?.uid ?: return
        if (lm.collected) return

        //Instant UI update
        landmarks.replaceAll {
            if (it.id == lm.id) it.copy(collected = true) else it
        }

        //Energy
        energy -= 125
        authRepository.updateEnergy(uid, deltaEnergy = -125)

        //Persist landmark
        authRepository.addLandmarkForUser(
            regionId = regionId,
            landmarkId = lm.id,
            userUid = uid
        )

        //Sync DB → UI
        collectedLandmarkIds.add(lm.id)
        authRepository.getCollectedLandmarks(regionId, uid) {
            collectedLandmarkIds.clear()
            collectedLandmarkIds.addAll(it)

            if (it.size == totalItems) {
                completeRegion(uid)
            }
        }

        explorationProgress = min(100, explorationProgress + 10)
    }

    // ─────────────────────────────────────────────
    // UI
    // ─────────────────────────────────────────────
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFA8CCA8), Color(0xFF4A6B4A))
                )
            )
    ) {

        MapDetailHeaderM(
            progress = explorationProgress,
            energy = energy,
            onBack = { navController.navigateUp() }
        )

        BoxWithConstraints(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            listOf(Color(0x4482A864), Color.Transparent)
                        )
                    )
            )

            landmarks.forEach { landmark ->
                LandmarkNodeM(
                    landmark = landmark,
                    width = constraints.maxWidth,
                    height = constraints.maxHeight,
                    onClick = {
                        selectedLandmark = landmark
                        dialogOpen = true
                    }
                )
            }
        }

        MapDetailBottomStatsM(
            collected = collectedItems,
            total = totalItems
        )
    }

    if (dialogOpen) {
        LandmarkDetailDialogM(
            landmark = selectedLandmark,
            onDismiss = { dialogOpen = false },
            onCollect = {
                collectLandmark()
                dialogOpen = false
            }
        )
    }
}
