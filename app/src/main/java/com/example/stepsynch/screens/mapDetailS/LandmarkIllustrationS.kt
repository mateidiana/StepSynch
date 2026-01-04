package com.example.stepsynch.screens.mapDetailS

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsS.CelestialPathIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.CloudArborIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.FloatingTerraceIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.HangingWaterfallIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.SkyOrchardIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.StarflowerMeadowIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.SunlitConservatoryIcon
import com.example.stepsynch.screens.landmarkIllustrationsS.WindChimeGroveIcon

@Composable
fun LandmarkIllustrationS(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Celestial Path" -> CelestialPathIcon(modifier, tint)
        "Cloud Arbor" -> CloudArborIcon(modifier, tint)
        "Floating Terrace" -> FloatingTerraceIcon(modifier, tint)
        "Hanging Waterfall" -> HangingWaterfallIcon(modifier, tint)
        "Sky Orchard" -> SkyOrchardIcon(modifier, tint)
        "Starflower Meadow" -> StarflowerMeadowIcon(modifier, tint)
        "Sunlit Conservatory" -> SunlitConservatoryIcon(modifier, tint)
        "Wind Chime Grove" -> WindChimeGroveIcon(modifier, Color(0xFF3E5622))
    }
}