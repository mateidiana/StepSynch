//package com.example.stepsynch.screens.mapDetailP
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.navigation.NavController
//import com.example.stepsynch.models.Landmark
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import kotlin.math.min
//import androidx.compose.ui.graphics.Color
//import androidx.compose.foundation.layout.BoxWithConstraints
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.Box
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import com.example.stepsynch.models.UserStatsGame
//import com.example.stepsynch.repository.AuthRepository
//import com.example.stepsynch.screens.mapDetailP.LandmarkDetailDialogP
//import com.example.stepsynch.screens.mapDetailP.LandmarkNodeP
//import com.example.stepsynch.screens.mapDetailP.MapDetailBottomStatsP
//import com.example.stepsynch.screens.mapDetailP.MapDetailHeaderP
//
//@Composable
//fun MapDetailViewScreenP (
//    navController: NavController, regionId: Int, authRepository: AuthRepository
//) {
//    val currentUser by authRepository.currentUser.collectAsState()
//    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
//
//    LaunchedEffect(currentUser) {
//        currentUser?.uid?.let { uid ->
//            authRepository.getUserGameStats(uid) { userGameStats ->
//                gameStats = userGameStats
//            }
//        }
//    }
//
//    var energy = gameStats?.energyPoints ?: 0
//    //var energy by remember { mutableStateOf(2450) }
//    var explorationProgress by remember { mutableStateOf(65) }
//    var collectedItems by remember { mutableStateOf(0) }
//    val totalItems = 8
//
//    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
//    var dialogOpen by remember { mutableStateOf(false) }
//
//    val landmarks = remember {
//        mutableStateListOf(
//            Landmark(1, "Endless Horizon", false, 0.20f, 0.30f),
//            Landmark(2, "Glacier Shelf", false, 0.50f, 0.50f),
//            Landmark(3, "High Camp", false, 0.70f, 0.25f),
//            Landmark(4, "Icefall Cliffs", false, 0.35f, 0.60f),
//            Landmark(5, "Knife Edge Pass", false, 0.75f, 0.65f),
//            Landmark(6, "Summit Cairn", false, 0.15f, 0.75f),
//            Landmark(7, "Summit Cross", false, 0.50f, 0.15f),
//            Landmark(8, "Wind Carved Ridge", false, 0.65f, 0.80f),
//        )
//    }
//
//    fun collectLandmark() {
//        selectedLandmark?.let { lm ->
//            val index = landmarks.indexOfFirst { it.id == lm.id }
//            if (index != -1 && !lm.collected) {
//                landmarks[index] = lm.copy(collected = true)
//                collectedItems++
//                explorationProgress = min(100, explorationProgress + 10)
//                energy -= 125
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                Brush.verticalGradient(
//                    colors = listOf(
//                        Color(0xFFA8CCA8), // light brown base
//                        Color(0xFF4A6B4A)  // slightly richer brown
//                    )
//                )
//            )
//    ) {
//
//        MapDetailHeaderP(
//            progress = explorationProgress,
//            energy = energy,
//            onBack = { navController.navigateUp() }
//        )
//
//        BoxWithConstraints(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//        ) {
//
//            // Background terrain
//            Box(
//                Modifier
//                    .fillMaxSize()
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(
//                                Color(0x4482A864),
//                                Color.Transparent
//                            )
//                        )
//                    )
//            )
//
//            landmarks.forEach { landmark ->
//                LandmarkNodeP(
//                    landmark = landmark,
//                    width = constraints.maxWidth,
//                    height = constraints.maxHeight,
//                    onClick = {
//                        selectedLandmark = landmark
//                        dialogOpen = true
//                    }
//                )
//            }
//
//            // Player indicator
////            PlayerIndicator(
////                x = 6.35f,
////                y = 0.60f,
////                width = constraints.maxWidth,
////                height = constraints.maxHeight
////            )
//        }
//
//        MapDetailBottomStatsP(
//            collected = collectedItems,
//            total = totalItems
//        )
//    }
//
//    if (dialogOpen) {
//        LandmarkDetailDialogP(
//            landmark = selectedLandmark,
//            onDismiss = { dialogOpen = false },
//            onCollect = {
//                collectLandmark()
//                dialogOpen = false
//            }
//        )
//    }
//}
package com.example.stepsynch.screens.mapDetailP

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
fun MapDetailViewScreenP(
    navController: NavController,
    regionId: Int,
    authRepository: AuthRepository
) {
    val currentUser by authRepository.currentUser.collectAsState()

    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
    var energy by remember { mutableStateOf(0) }

    val collectedLandmarkIds = remember { mutableStateListOf<Int>() }

    // ─────────────────────────────────────────────
    // Load user stats + collected landmarks
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

    // 🔑 DB → UI sync
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

        // 1️⃣ Immediate UI update
        landmarks.replaceAll {
            if (it.id == lm.id) it.copy(collected = true) else it
        }

        // 2️⃣ Energy update
        energy -= 250
        authRepository.updateEnergy(uid, deltaEnergy = -250)

        // 3️⃣ Persist landmark
        authRepository.addLandmarkForUser(
            regionId = regionId,
            landmarkId = lm.id,
            userUid = uid
        )

        // 4️⃣ Sync DB + completion check
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
                    listOf(
                        Color(0xFFA8CCA8),
                        Color(0xFF4A6B4A)
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
