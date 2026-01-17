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
        Region(1, "Welcome Park", 0, 0, RegionType.NATURE, RegionStatus.COMPLETED, 100, 0.50f, 0.85f, false, 0),
        Region(2, "Riverside Trail", 800, 8000, RegionType.NATURE, RegionStatus.EXPLORING, 65, 0.50f, 0.70f, false, 250),
        Region(3, "Downtown Plaza", 1000, 10000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.30f, 0.55f, false, 250),
        Region(4, "Mountain Path", 1000, 10000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.70f, 0.55f, false,250),
        Region(5, "City Center", 1200, 12000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.30f, 0.40f, false, 500),
        Region(6, "Forest Grove", 1200, 12000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.50f, 0.40f, false,500),
        Region(7, "Harbor District", 1200, 12000, RegionType.CITY, RegionStatus.UNLOCKED, 0, 0.70f, 0.40f, false, 500),
        Region(8, "Sky Gardens", 2000, 20000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.35f, 0.25f, true, 1000),
        Region(9, "Ancient Ruins", 2000, 20000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.65f, 0.25f, true, 1000),
        Region(10, "Summit Peak", 2000, 20000, RegionType.NATURE, RegionStatus.UNLOCKED, 0, 0.50f, 0.10f, true, 1000)
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