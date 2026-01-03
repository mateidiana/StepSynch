package com.example.stepsynch.screens.landmarkIllustrationsC

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun StreetCrossingIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Road base
        drawRoundRect(
            color = color.copy(alpha = 0.85f),
            topLeft = Offset(w * 0.12f, h * 0.55f),
            size = Size(w * 0.76f, h * 0.18f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // Vertical road
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.42f, h * 0.32f),
            size = Size(w * 0.16f, h * 0.48f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // Zebra stripes
        repeat(4) { i ->
            drawRoundRect(
                color = Color.White.copy(alpha = 0.3f),
                topLeft = Offset(w * (0.44f + i * 0.03f), h * 0.5f),
                size = Size(w * 0.02f, h * 0.3f),
                cornerRadius = CornerRadius(6f, 6f)
            )
        }
    }
}

