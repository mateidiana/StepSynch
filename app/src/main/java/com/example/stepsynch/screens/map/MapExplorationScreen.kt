package com.example.stepsynch.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun MapExplorationScreen(
    navController: NavController,
    viewModel: MapViewModel = viewModel()
) {
    val energy by viewModel.currentEnergy.collectAsState()
    val steps by viewModel.todaySteps.collectAsState()
    val selectedRegion by viewModel.selectedRegion.collectAsState()

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
                onClose = viewModel::clearSelection
            )
        }
    }
}
