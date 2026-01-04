package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun HangingWaterfallIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Floating ledge ---
        drawRoundRect(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.35f, h * 0.25f),
            size = Size(w * 0.3f, h * 0.1f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
        )

        // --- Waterfall gradient ---
        val waterGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB3E5FC),
                Color(0xFF81D4FA),
                Color.Transparent
            )
        )

        drawRect(
            brush = waterGradient,
            topLeft = Offset(w * 0.46f, h * 0.35f),
            size = Size(w * 0.08f, h * 0.45f)
        )

        // --- Mist highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.4f, h * 0.75f),
            size = Size(w * 0.2f, h * 0.08f)
        )
    }
}