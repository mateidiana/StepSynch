package com.example.stepsynch.screens.mapDetailA

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsA.BrokenObeliskIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.CrumblingGateIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.ForgottenAltarIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.MosaicFloorIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.OvergrownLibraryIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.PillarOfAgesIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.SunkenCourtyardIcon
import com.example.stepsynch.screens.landmarkIllustrationsA.WhisperingArchwayIcon

@Composable
fun LandmarkIllustrationA(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Broken Obelisk" -> BrokenObeliskIcon(modifier, tint)
        "Crumbling Gate" -> CrumblingGateIcon(modifier, tint)
        "Forgotten Altar" -> ForgottenAltarIcon(modifier, tint)
        "Mosaic Floor" -> MosaicFloorIcon(modifier, tint)
        "Overgrown Library" -> OvergrownLibraryIcon(modifier, tint)
        "Pillar of Ages" -> PillarOfAgesIcon(modifier, tint)
        "Sunken Courtyard" -> SunkenCourtyardIcon(modifier, tint)
        "Whispering Archway" -> WhisperingArchwayIcon(modifier, tint)
    }
}