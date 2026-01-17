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
fun GlacierPointIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Back ice mass ---
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.28f, h * 0.22f),
            size = Size(w * 0.44f, h * 0.6f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // --- Main glacier face ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.32f, h * 0.2f),
            size = Size(w * 0.36f, h * 0.62f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // --- Vertical ice facet ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.45f, h * 0.25f),
            size = Size(w * 0.06f, h * 0.45f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // --- Frost highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.22f),
            radius = w * 0.18f,
            center = Offset(w * 0.5f, h * 0.24f)
        )
    }
}

