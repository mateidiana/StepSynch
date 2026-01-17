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
fun CourtyardIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Outer enclosure
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.12f, h * 0.32f),
            size = Size(w * 0.76f, h * 0.45f),
            cornerRadius = CornerRadius(26f, 26f)
        )

        // Inner wall
        drawRoundRect(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.2f, h * 0.4f),
            size = Size(w * 0.6f, h * 0.3f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // Courtyard ground
        drawCircle(
            color = Color.White.copy(alpha = 0.18f),
            radius = w * 0.17f,
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // Center feature (fountain / garden)
        drawCircle(
            color = Color.White.copy(alpha = 0.3f),
            radius = w * 0.08f,
            center = Offset(w * 0.5f, h * 0.55f)
        )
    }
}

