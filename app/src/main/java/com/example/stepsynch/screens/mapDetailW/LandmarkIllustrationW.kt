package com.example.stepsynch.screens.mapDetailW

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsW.FlowerGardenIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.MiniLakeIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.ObservationDeckIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.ParkEntranceArchIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.PicnicGroveIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.SculpturePathIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.SwingSetIcon
import com.example.stepsynch.screens.landmarkIllustrationsW.WelcomeFountainIcon

@Composable
fun LandmarkIllustrationW(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Flower Garden" -> FlowerGardenIcon(modifier, tint)
        "Mini Lake" -> MiniLakeIcon(modifier, tint)
        "Observation Deck" -> ObservationDeckIcon(modifier, tint)
        "Park Entrance Arch" -> ParkEntranceArchIcon(modifier, tint)
        "Picnic Grove" -> PicnicGroveIcon(modifier, tint)
        "Sculpture Path" -> SculpturePathIcon(modifier, tint)
        "Swing Set" -> SwingSetIcon(modifier, Color(0xFF3E5622))
        "Welcome Fountain" -> WelcomeFountainIcon(modifier, tint)
    }
}