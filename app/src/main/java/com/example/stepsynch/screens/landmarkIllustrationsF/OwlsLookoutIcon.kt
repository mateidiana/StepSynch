package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius

@Composable
fun OwlsLookoutIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Trunk gradient ---
        val trunkGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF5A3A1A),
                Color(0xFF3E5622)
            )
        )

        // --- Main trunk ---
        drawRoundRect(
            brush = trunkGradient,
            topLeft = Offset(w * 0.4f, h * 0.2f),
            size = Size(w * 0.2f, h * 0.65f),
            cornerRadius = CornerRadius(30f, 30f)
        )

        // --- Tree crown ---
        drawCircle(
            color = color.copy(alpha = 0.9f),
            radius = w * 0.28f,
            center = Offset(w * 0.5f, h * 0.22f)
        )

        // --- Hollow (owl nest) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.45f),
            topLeft = Offset(w * 0.46f, h * 0.45f),
            size = Size(w * 0.08f, h * 0.12f)
        )

        // --- Hollow rim highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.46f, h * 0.45f),
            size = Size(w * 0.08f, h * 0.12f)
        )
    }
}