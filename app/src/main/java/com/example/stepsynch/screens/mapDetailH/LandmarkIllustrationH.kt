package com.example.stepsynch.screens.mapDetailH

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsH.CoastalBreakwaterIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.DocksidePiersIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.FishingBoatsIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.HarborMarketIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.OldLighthouseIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.SailorsMonumentIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.ShipyardCraneIcon
import com.example.stepsynch.screens.landmarkIllustrationsH.TidalPoolsIcon

@Composable
fun LandmarkIllustrationH(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Coastal Breakwater" -> CoastalBreakwaterIcon(modifier, tint)
        "Dockside Piers" -> DocksidePiersIcon(modifier, Color(0xFF444040))
        "Fishing Boats" -> FishingBoatsIcon(modifier, Color(0xFF5A3A1A))
        "Harbor Market" -> HarborMarketIcon(modifier, tint)
        "Old Lighthouse" -> OldLighthouseIcon(modifier, Color(0xFF444040))
        "Sailors Monument" -> SailorsMonumentIcon(modifier, Color(0xFF444040))
        "Shipyard Crane" -> ShipyardCraneIcon(modifier, tint)
        "Tidal Pools" -> TidalPoolsIcon(modifier, tint)
    }
}