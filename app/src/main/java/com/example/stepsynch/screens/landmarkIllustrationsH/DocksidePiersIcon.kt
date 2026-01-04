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
fun DocksidePiersIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Water base ---
        drawRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(0f, h * 0.45f),
            size = Size(w, h * 0.55f)
        )

        // --- Pier shadow ---
        drawRoundRect(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.12f, h * 0.5f),
            size = Size(w * 0.76f, h * 0.12f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // --- Pier planks ---
        val pierGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF6D4C2F),
                Color(0xFF5A3A1A)
            )
        )

        drawRoundRect(
            brush = pierGradient,
            topLeft = Offset(w * 0.1f, h * 0.45f),
            size = Size(w * 0.8f, h * 0.12f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // --- Pilings ---
        listOf(0.18f, 0.38f, 0.58f, 0.78f).forEach {
            drawRect(
                color = Color(0xFF4A2F16),
                topLeft = Offset(w * it, h * 0.57f),
                size = Size(w * 0.04f, h * 0.2f)
            )
        }

        // --- Water highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.2f, h * 0.6f),
            size = Size(w * 0.6f, h * 0.2f)
        )
    }
}