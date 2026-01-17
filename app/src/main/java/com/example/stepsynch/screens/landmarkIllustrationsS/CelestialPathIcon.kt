package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun CelestialPathIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val pathGlow = Brush.linearGradient(
            colors = listOf(
                color.copy(alpha = 0.25f),
                color.copy(alpha = 0.8f),
                color.copy(alpha = 0.25f)
            ),
            start = Offset(w * 0.3f, h),
            end = Offset(w * 0.7f, 0f)
        )

        // --- Path ---
        drawRoundRect(
            brush = pathGlow,
            topLeft = Offset(w * 0.42f, h * 0.15f),
            size = Size(w * 0.16f, h * 0.7f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(40f, 40f)
        )

        // --- Stepping lights ---
        repeat(4) { i ->
            drawCircle(
                color = Color.White.copy(alpha = 0.35f),
                radius = w * 0.025f,
                center = Offset(w * 0.5f, h * (0.25f + i * 0.15f))
            )
        }
    }
}