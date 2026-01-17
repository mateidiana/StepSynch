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
fun ClockTowerIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Base shadow
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.4f, h * 0.62f),
            size = Size(w * 0.2f, h * 0.18f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // Main tower
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.42f, h * 0.22f),
            size = Size(w * 0.16f, h * 0.58f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // Vertical highlight
        drawRoundRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.46f, h * 0.25f),
            size = Size(w * 0.03f, h * 0.5f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // Clock rim
        drawCircle(
            color = color.copy(alpha = 0.85f),
            radius = w * 0.14f,
            center = Offset(w * 0.5f, h * 0.36f)
        )

        // Clock face
        drawCircle(
            color = Color.White.copy(alpha = 0.28f),
            radius = w * 0.11f,
            center = Offset(w * 0.5f, h * 0.36f)
        )

        // Tower crown
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.4f, h * 0.16f),
            size = Size(w * 0.2f, h * 0.08f),
            cornerRadius = CornerRadius(12f, 12f)
        )
    }
}

