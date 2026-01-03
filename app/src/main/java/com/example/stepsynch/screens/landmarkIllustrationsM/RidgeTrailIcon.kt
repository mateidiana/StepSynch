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
fun RidgeTrailIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Back ridge ---
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.13f, h * 0.42f),
            size = Size(w * 0.74f, h * 0.18f),
            cornerRadius = CornerRadius(24f, 24f)
        )

        // --- Main ridge ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.17f, h * 0.46f),
            size = Size(w * 0.66f, h * 0.18f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // --- Trail path (slightly winding) ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.46f, h * 0.36f),
            size = Size(w * 0.08f, h * 0.42f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // --- Crest highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.15f),
            radius = w * 0.18f,
            center = Offset(w * 0.55f, h * 0.4f)
        )
    }
}

