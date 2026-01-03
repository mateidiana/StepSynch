package com.example.stepsynch.screens.mapDetailF

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsF.FallenLogCrossingIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.FernHollowIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.ForestStreamIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.MossyClearingIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.SunbeamGladeIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.WhisperingPinesIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.WildflowerPatchIcon
import com.example.stepsynch.screens.landmarkIllustrationsF.OwlsLookoutIcon

@Composable
fun LandmarkIllustrationF(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Fallen Log Crossing" -> FallenLogCrossingIcon(modifier, tint)
        "Fern Hollow" -> FernHollowIcon(modifier, tint)
        "Forest Stream" -> ForestStreamIcon(modifier, tint)
        "Mossy Clearing" -> MossyClearingIcon(modifier, Color(0xFF3E5622))
        "Sunbeam Glade" -> SunbeamGladeIcon(modifier, tint)
        "Whispering Pines" -> WhisperingPinesIcon(modifier, tint)
        "Wildflower Patch" -> WildflowerPatchIcon(modifier, Color(0xFF5A3A1A))
        "Owls Lookout" -> OwlsLookoutIcon(modifier, tint)
    }
}