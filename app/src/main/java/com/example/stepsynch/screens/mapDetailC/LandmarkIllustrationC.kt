package com.example.stepsynch.screens.mapDetailC

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsC.StreetCrossingIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.LibraryIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.CourtyardIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.CityMarketIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.ClockTowerIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.RooftopGardenIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.SkylineViewIcon
import com.example.stepsynch.screens.landmarkIllustrationsC.TransitHubIcon

@Composable
fun LandmarkIllustrationC(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Street Crossing" -> StreetCrossingIcon(modifier, Color(0xFF444040))
        "Library" -> LibraryIcon(modifier, Color(0xFF5A3A1A))
        "Courtyard" -> CourtyardIcon(modifier, tint)
        "City Market" -> CityMarketIcon(modifier, tint)
        "Clock Tower" -> ClockTowerIcon(modifier, Color(0xFF5A3A1A))
        "Rooftop Garden" -> RooftopGardenIcon(modifier, Color(0xFF3E5622))
        "Skyline View" -> SkylineViewIcon(modifier, Color(0xFF444040))
        "Transit Hub" -> TransitHubIcon(modifier, Color(0xFF444040))
    }
}