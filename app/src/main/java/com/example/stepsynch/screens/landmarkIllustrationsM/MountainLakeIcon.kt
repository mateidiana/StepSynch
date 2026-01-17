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
fun MountainLakeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Shore / back water ---
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.18f, h * 0.43f),
            size = Size(w * 0.64f, h * 0.28f),
            cornerRadius = CornerRadius(42f, 42f)
        )

        // --- Main lake ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.22f, h * 0.48f),
            size = Size(w * 0.56f, h * 0.28f),
            cornerRadius = CornerRadius(40f, 40f)
        )

        // --- Deep center ---
        drawOval(
            color = Color.Black.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.35f, h * 0.55f),
            size = Size(w * 0.3f, h * 0.15f)
        )

        // --- Reflection highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.18f),
            radius = w * 0.2f,
            center = Offset(w * 0.48f, h * 0.5f)
        )
    }
}

