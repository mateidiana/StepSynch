package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun GlacierShelfIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Base ice gradient ---
        val iceGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB3E5FC),
                Color(0xFF81D4FA)
            )
        )

        drawRect(
            brush = iceGradient,
            topLeft = Offset(w * 0.2f, h * 0.4f),
            size = Size(w * 0.6f, h * 0.3f)
        )

        // --- Cracks / fissures ---
        drawLine(
            color = Color.White.copy(alpha = 0.4f),
            start = Offset(w * 0.25f, h * 0.42f),
            end = Offset(w * 0.75f, h * 0.58f),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.White.copy(alpha = 0.35f),
            start = Offset(w * 0.3f, h * 0.45f),
            end = Offset(w * 0.7f, h * 0.55f),
            strokeWidth = 1.5f
        )

        // --- Shadow under ice shelf ---
        drawRect(
            color = Color.Black.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.2f, h * 0.65f),
            size = Size(w * 0.6f, h * 0.05f)
        )
    }
}