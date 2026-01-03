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
fun RockyOutcropIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Back rock ---
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.12f, h * 0.48f),
            size = Size(w * 0.32f, h * 0.3f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // --- Mid rock ---
        drawRoundRect(
            color = color.copy(alpha = 0.8f),
            topLeft = Offset(w * 0.38f, h * 0.45f),
            size = Size(w * 0.36f, h * 0.34f),
            cornerRadius = CornerRadius(16f, 16f)
        )

        // --- Front rock ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.55f, h * 0.52f),
            size = Size(w * 0.28f, h * 0.28f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // --- Rock face highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.14f),
            radius = w * 0.16f,
            center = Offset(w * 0.62f, h * 0.46f)
        )
    }
}

