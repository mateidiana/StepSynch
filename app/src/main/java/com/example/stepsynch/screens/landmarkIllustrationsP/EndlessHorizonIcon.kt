package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun EndlessHorizonIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Foreground rock edge ---
        drawRect(
            color = Color(0xFF6E6E6E),
            topLeft = Offset(w * 0.1f, h * 0.55f),
            size = Size(w * 0.8f, h * 0.2f)
        )

        // --- Mid-distance layers (mist / ridges) ---
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE0E0E0).copy(alpha = 0.4f),
                    Color(0xFFB0B0B0).copy(alpha = 0.2f)
                )
            ),
            topLeft = Offset(w * 0.15f, h * 0.35f),
            size = Size(w * 0.7f, h * 0.2f)
        )

        // --- Distant horizon ---
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFB0C4DE).copy(alpha = 0.25f),
                    Color(0xFF87CEFA).copy(alpha = 0.15f)
                )
            ),
            topLeft = Offset(w * 0f, h * 0.15f),
            size = Size(w, h * 0.2f)
        )

        // --- Snow highlight on rock edge ---
        drawRect(
            color = Color.White.copy(alpha = 0.1f),
            topLeft = Offset(w * 0.1f, h * 0.55f),
            size = Size(w * 0.8f, h * 0.05f)
        )
    }
}