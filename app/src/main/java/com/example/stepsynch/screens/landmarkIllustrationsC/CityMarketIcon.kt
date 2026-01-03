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
fun CityMarketIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        repeat(3) { i ->
            val x = w * (0.2f + i * 0.2f)

            // Stall base (depth layer)
            drawRoundRect(
                color = color.copy(alpha = 0.6f),
                topLeft = Offset(x, h * 0.6f),
                size = Size(w * 0.16f, h * 0.18f),
                cornerRadius = CornerRadius(14f, 14f)
            )

            // Stall front
            drawRoundRect(
                color = color.copy(alpha = 0.9f),
                topLeft = Offset(x, h * 0.5f),
                size = Size(w * 0.16f, h * 0.25f),
                cornerRadius = CornerRadius(16f, 16f)
            )

            // Divider
            drawRoundRect(
                color = Color.White.copy(alpha = 0.15f),
                topLeft = Offset(x + w * 0.075f, h * 0.52f),
                size = Size(w * 0.01f, h * 0.2f),
                cornerRadius = CornerRadius(4f, 4f)
            )
        }

        // Main canopy
        drawRoundRect(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.16f, h * 0.43f),
            size = Size(w * 0.68f, h * 0.1f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // Canopy highlight stripes
        repeat(3) { i ->
            drawRoundRect(
                color = Color.White.copy(alpha = 0.12f),
                topLeft = Offset(w * (0.22f + i * 0.18f), h * 0.44f),
                size = Size(w * 0.08f, h * 0.08f),
                cornerRadius = CornerRadius(12f, 12f)
            )
        }
    }
}

