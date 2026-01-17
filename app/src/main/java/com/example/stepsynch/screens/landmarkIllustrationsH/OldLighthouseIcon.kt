package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun OldLighthouseIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Base shadow ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.3f, h * 0.78f),
            size = Size(w * 0.4f, h * 0.12f)
        )

        // --- Lighthouse tower gradient ---
        val towerGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFEEEEEE),
                Color(0xFFCCCCCC),
                Color(0xFF9E9E9E)
            )
        )

        drawRoundRect(
            brush = towerGradient,
            topLeft = Offset(w * 0.42f, h * 0.18f),
            size = Size(w * 0.16f, h * 0.62f),
            cornerRadius = CornerRadius(24f, 24f)
        )

        // --- Lantern room ---
        drawRoundRect(
            color = Color(0xFF5A5A5A),
            topLeft = Offset(w * 0.39f, h * 0.1f),
            size = Size(w * 0.22f, h * 0.12f),
            cornerRadius = CornerRadius(16f, 16f)
        )

        // --- Beacon glow ---
        drawCircle(
            color = Color(0xFFFFF3C0).copy(alpha = 0.35f),
            radius = w * 0.16f,
            center = Offset(w * 0.5f, h * 0.16f)
        )

        // --- Subtle highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.46f, h * 0.25f),
            size = Size(w * 0.06f, h * 0.45f),
            cornerRadius = CornerRadius(12f, 12f)
        )
    }
}