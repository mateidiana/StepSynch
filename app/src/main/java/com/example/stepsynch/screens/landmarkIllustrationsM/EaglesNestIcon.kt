package com.example.stepsynch.screens.landmarkIllustrationsM

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun EaglesNestIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Back cliff layer ---
        drawRoundRect(
            color = color.copy(alpha = 0.55f),
            topLeft = Offset(w * 0.18f, h * 0.48f),
            size = Size(w * 0.64f, h * 0.28f),
            cornerRadius = CornerRadius(16f, 16f)
        )

        // --- Front cliff ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.22f, h * 0.55f),
            size = Size(w * 0.56f, h * 0.28f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // --- Nest base ---
        val nestCenter = Offset(w * 0.72f, h * 0.48f)
        drawCircle(
            color = Color(0xFFB08B4F).copy(alpha = 0.9f),
            radius = w * 0.11f,
            center = nestCenter
        )

        // --- Nest inner texture ---
        drawCircle(
            color = Color(0xFF8C6A3D).copy(alpha = 0.8f),
            radius = w * 0.075f,
            center = nestCenter
        )

        // --- Highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.15f),
            radius = w * 0.18f,
            center = Offset(w * 0.6f, h * 0.4f)
        )
    }
}

