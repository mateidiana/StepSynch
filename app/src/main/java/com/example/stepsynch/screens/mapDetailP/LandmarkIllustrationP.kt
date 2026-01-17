package com.example.stepsynch.screens.mapDetailP

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsP.EndlessHorizonIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.GlacierShelfIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.HighCampIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.IcefallCliffsIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.KnifeEdgePassIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.SummitCrossIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.SummitCairnIcon
import com.example.stepsynch.screens.landmarkIllustrationsP.WindCarvedRidgeIcon

@Composable
fun LandmarkIllustrationP(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Endless Horizon" -> EndlessHorizonIcon(modifier, tint)
        "Glacier Shelf" -> GlacierShelfIcon(modifier, tint)
        "High Camp" -> HighCampIcon(modifier, tint)
        "Icefall Cliffs" -> IcefallCliffsIcon(modifier, tint)
        "Knife Edge Pass" -> KnifeEdgePassIcon(modifier, tint)
        "Summit Cross" -> SummitCrossIcon(modifier, tint)
        "Summit Cairn" -> SummitCairnIcon(modifier, tint)
        "Wind Carved Ridge" -> WindCarvedRidgeIcon(modifier, tint)
    }
}