package com.example.stepsynch.screens.mapDetailM

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsM.RidgeTrailIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.AlpineMeadowIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.BoulderFieldIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.EaglesNestIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.GlacierPointIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.MountainLakeIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.PineGroveIcon
import com.example.stepsynch.screens.landmarkIllustrationsM.RockyOutcropIcon

@Composable
fun LandmarkIllustrationM(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Ridge Trail" -> RidgeTrailIcon(modifier, tint)
        "Alpine Meadow" -> AlpineMeadowIcon(modifier, Color(0xFF3E5622))
        "Boulder Field" -> BoulderFieldIcon(modifier, tint)
        "Eagles Nest" -> EaglesNestIcon(modifier, tint)
        "Glacier Point" -> GlacierPointIcon(modifier, Color(0xFF2F5D6B))
        "Mountain Lake" -> MountainLakeIcon(modifier, Color(0xFF2F5D6B))
        "Pine Grove" -> PineGroveIcon(modifier, tint)
        "Rocky Outcrop" -> RockyOutcropIcon(modifier, tint)
    }
}