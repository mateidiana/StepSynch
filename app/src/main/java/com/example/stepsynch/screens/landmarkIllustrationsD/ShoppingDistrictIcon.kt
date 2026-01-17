package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun ShoppingDistrictIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Base buildings
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.1f, h * 0.35f),
            size = Size(w * 0.8f, h * 0.4f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // Awnings
        val awningColor = Color.White.copy(alpha = 0.25f)
        for (i in 0..2) {
            drawRoundRect(
                color = awningColor,
                topLeft = Offset(w * (0.18f + i * 0.22f), h * 0.28f),
                size = Size(w * 0.18f, h * 0.1f),
                cornerRadius = CornerRadius(8f, 8f)
            )
        }

        // Door accents
        for (i in 0..2) {
            drawRoundRect(
                color = Color.Black.copy(alpha = 0.15f),
                topLeft = Offset(w * (0.22f + i * 0.22f), h * 0.48f),
                size = Size(w * 0.1f, h * 0.22f),
                cornerRadius = CornerRadius(6f, 6f)
            )
        }
    }
}
