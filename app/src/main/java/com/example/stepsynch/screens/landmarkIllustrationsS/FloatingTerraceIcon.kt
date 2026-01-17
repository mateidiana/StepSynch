package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun FloatingTerraceIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Floating shadow ---
        drawOval(
            color = Color.Black.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.28f, h * 0.68f),
            size = Size(w * 0.44f, h * 0.12f)
        )

        // --- Terrace base ---
        val baseGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFBDBDBD),
                Color(0xFF9E9E9E)
            )
        )

        drawRoundRect(
            brush = baseGradient,
            topLeft = Offset(w * 0.25f, h * 0.45f),
            size = Size(w * 0.5f, h * 0.16f),
            cornerRadius = CornerRadius(28f, 28f)
        )

        // --- Garden top ---
        drawRoundRect(
            color = color.copy(alpha = 0.85f),
            topLeft = Offset(w * 0.27f, h * 0.42f),
            size = Size(w * 0.46f, h * 0.08f),
            cornerRadius = CornerRadius(24f, 24f)
        )

        // --- Trailing plants ---
        drawCircle(
            color = color.copy(alpha = 0.7f),
            radius = w * 0.05f,
            center = Offset(w * 0.32f, h * 0.55f)
        )
        drawCircle(
            color = color.copy(alpha = 0.6f),
            radius = w * 0.04f,
            center = Offset(w * 0.65f, h * 0.56f)
        )

        // --- Soft highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.32f, h * 0.46f),
            size = Size(w * 0.36f, h * 0.05f),
            cornerRadius = CornerRadius(20f, 20f)
        )
    }
}