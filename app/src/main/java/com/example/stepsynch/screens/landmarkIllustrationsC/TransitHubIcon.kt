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
fun TransitHubIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Platform base
        drawRoundRect(
            color = color.copy(alpha = 0.7f),
            topLeft = Offset(w * 0.12f, h * 0.6f),
            size = Size(w * 0.76f, h * 0.18f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // Platform top
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.15f, h * 0.55f),
            size = Size(w * 0.7f, h * 0.2f),
            cornerRadius = CornerRadius(22f, 22f)
        )

        // Central pillar
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.46f, h * 0.28f),
            size = Size(w * 0.08f, h * 0.4f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // Canopy
        drawRoundRect(
            color = Color.White.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.3f, h * 0.24f),
            size = Size(w * 0.4f, h * 0.08f),
            cornerRadius = CornerRadius(20f, 20f)
        )
    }
}

