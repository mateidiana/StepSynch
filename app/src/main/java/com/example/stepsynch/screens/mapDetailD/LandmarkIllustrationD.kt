package com.example.stepsynch.screens.mapDetailD

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.landmarkIllustrationsD.TheaterIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.CityHallIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.ArtGalleryIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.CoffeeShopIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.CentralParkIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.FountainSquareIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.HistoricMonumentIcon
import com.example.stepsynch.screens.landmarkIllustrationsD.ShoppingDistrictIcon

@Composable
fun LandmarkIllustrationD(
    name: String,
    collected: Boolean,
    modifier: Modifier
) {
    val tint = if (collected) Color(0xFF83781B) else Color(0xFF83781B)

    when (name) {
        "Theater" -> TheaterIcon(modifier, tint)
        "City Hall" -> CityHallIcon(modifier, tint)
        "Art Gallery" -> ArtGalleryIcon(modifier, tint)
        "Coffee Shop" -> CoffeeShopIcon(modifier, tint)
        "Central Park" -> CentralParkIcon(modifier, Color(0xFF3E5622))
        "Fountain Square" -> FountainSquareIcon(modifier, Color(0xFF3E5622))
        "Historic Monument" -> HistoricMonumentIcon(modifier, tint)
        "Shopping District" -> ShoppingDistrictIcon(modifier, tint)
    }
}