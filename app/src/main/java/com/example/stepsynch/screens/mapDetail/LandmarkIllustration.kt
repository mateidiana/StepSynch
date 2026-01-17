package com.example.stepsynch.screens.mapDetail

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrations.AncientOakIcon
import com.example.stepsynch.screens.landmarkIllustrations.CaveIcon
import com.example.stepsynch.screens.landmarkIllustrations.CrystalLakeIcon
import com.example.stepsynch.screens.landmarkIllustrations.LookoutIcon
import com.example.stepsynch.screens.landmarkIllustrations.OakTreeIcon
import com.example.stepsynch.screens.landmarkIllustrations.StoneBridgeIcon
import com.example.stepsynch.screens.landmarkIllustrations.SummitIcon
import com.example.stepsynch.screens.landmarkIllustrations.WaterfallIcon

@Composable
fun LandmarkIllustration(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Oak Tree" -> OakTreeIcon(modifier, Color(0xFF3E5622))
        "Ancient Oak" -> AncientOakIcon(modifier, Color(0xFF3E5622))
        "Stone Bridge" -> StoneBridgeIcon(modifier, tint)
        "Lookout Point" -> LookoutIcon(modifier, tint)
        "Hidden Cave" -> CaveIcon(modifier)
        "Waterfall" -> WaterfallIcon(modifier, Color(0xFF2F5D6B))
        "Summit" -> SummitIcon(modifier, tint)
        "Crystal Lake" -> CrystalLakeIcon(modifier, Color(0xFF2F5D6B))
    }
}
