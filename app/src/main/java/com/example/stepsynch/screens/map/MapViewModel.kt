package com.example.stepsynch.screens.map
import com.example.stepsynch.models.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel : ViewModel(){
    private val _currentEnergy = MutableStateFlow(2450)
    val currentEnergy = _currentEnergy.asStateFlow()

    private val _todaySteps = MutableStateFlow(8234)
    val todaySteps = _todaySteps.asStateFlow()

    private val _selectedRegion = MutableStateFlow<Region?>(null)
    val selectedRegion = _selectedRegion.asStateFlow()

    val regions = listOf(
        Region(1, "Welcome Park", 0, 0, RegionType.NATURE, RegionStatus.COMPLETED, 100, 0.50f, 0.85f),
        Region(2, "Riverside Trail", 500, 5000, RegionType.NATURE, RegionStatus.EXPLORING, 65, 0.50f, 0.70f),
        Region(3, "Downtown Plaza", 1000, 10000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.30f, 0.55f),
        Region(4, "Mountain Path", 1000, 10000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.70f, 0.55f),
        Region(5, "City Center", 1500, 20000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.30f, 0.40f),
        Region(6, "Forest Grove", 1500, 20000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.50f, 0.40f),
        Region(7, "Harbor District", 2000, 30000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.70f, 0.40f),
        Region(8, "Sky Gardens", 2000, 50000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.35f, 0.25f),
        Region(9, "Ancient Ruins", 2000, 75000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.65f, 0.25f),
        Region(10, "Summit Peak", 2000, 100000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.50f, 0.10f)
    )

    fun selectRegion(region: Region) {
        if (region.status != RegionStatus.LOCKED) {
            _selectedRegion.value = region
        }
    }

    fun clearSelection() {
        _selectedRegion.value = null
    }
}