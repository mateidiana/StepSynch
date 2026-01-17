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
fun LibraryIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Roof band
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.18f, h * 0.38f),
            size = Size(w * 0.64f, h * 0.1f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // Main building
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.2f, h * 0.45f),
            size = Size(w * 0.6f, h * 0.35f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // Inner façade
        drawRoundRect(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.25f, h * 0.5f),
            size = Size(w * 0.5f, h * 0.25f),
            cornerRadius = CornerRadius(16f, 16f)
        )

        // Book spines
        repeat(4) { i ->
            drawRoundRect(
                color = Color.White.copy(alpha = if (i % 2 == 0) 0.18f else 0.12f),
                topLeft = Offset(w * (0.3f + i * 0.1f), h * 0.52f),
                size = Size(w * 0.05f, h * 0.22f),
                cornerRadius = CornerRadius(8f, 8f)
            )
        }
    }
}

