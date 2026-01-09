package com.example.stepsynch.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.stepsynch.models.UserStatsGF
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.repository.AuthRepository

@Composable
fun MapExplorationScreen(
    navController: NavController,
    authRepository: AuthRepository,
    viewModel: MapViewModel = viewModel()
) {
    val currentUser by authRepository.currentUser.collectAsState()
    var stats by remember { mutableStateOf<UserStatsGF?>(null) }
    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
    var isCompleted by remember { mutableStateOf(false) }
    val selectedRegion by viewModel.selectedRegion.collectAsState()
    val regionId = selectedRegion?.id

    LaunchedEffect(currentUser, regionId) {
        val uid = currentUser?.uid
        val rid = regionId

        if (uid != null && rid != null) {
            authRepository.getUserStats(uid) { stats = it }
            authRepository.getUserGameStats(uid) { gameStats = it }
            authRepository.isRegionCompleted(rid, uid) {
                isCompleted = it
            }
        }
    }


    val steps = stats?.stepCountToday ?: 0
    val energy = gameStats?.energyPoints ?: 0
    //val energy by viewModel.currentEnergy.collectAsState()
    //val steps by viewModel.todaySteps.collectAsState()
    //val selectedRegion by viewModel.selectedRegion.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF8FAF6), Color(0x3395B46A))
                )
            )
    ) {
        MapHeader(energy, steps, onBack = { navController.navigateUp() })

        Box(modifier = Modifier.weight(1f)) {
            MapCanvas(
                regions = viewModel.regions,
                onRegionClick = viewModel::selectRegion
            )
        }

        selectedRegion?.let {
            RegionDetailPanel(
                region = it,
                energy = energy,
                isCompleted = isCompleted,
                onClose = viewModel::clearSelection,
                onStartExploring = { region ->
                    if (region.id == 1) { // Welcome Park
                        navController.navigate("mapDetailW/${region.id}")
                    }
                    if (region.id == 2) { // Riverside Trail
                        navController.navigate("mapDetail/${region.id}")
                    }
                    if (region.id == 3) { // Downtown Plaza
                        navController.navigate("mapDetailD/${region.id}")
                    }
                    if (region.id == 4) { // Mountain Path
                        navController.navigate("mapDetailM/${region.id}")
                    }
                    if (region.id == 5) { // City Center
                        navController.navigate("mapDetailC/${region.id}")
                    }
                    if (region.id == 6) { // Forest Grove
                        navController.navigate("mapDetailF/${region.id}")
                    }
                    if (region.id == 7) { // Harbor District
                        navController.navigate("mapDetailH/${region.id}")
                    }
                    if (region.id == 8) { // Sky Gardens
                        navController.navigate("mapDetailS/${region.id}")
                    }
                    if (region.id == 9) { // Ancient Ruins
                        navController.navigate("mapDetailA/${region.id}")
                    }
                    if (region.id == 10) { // Summit Peak
                        navController.navigate("mapDetailP/${region.id}")
                    }
                }
            )
        }
    }
}
