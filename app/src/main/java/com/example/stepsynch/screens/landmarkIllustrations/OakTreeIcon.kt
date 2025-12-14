package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun OakTreeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Canopy gradient ---
        val canopyGradient = Brush.radialGradient(
            colors = listOf(
                Color(0xFF4A5D23), // bright green
                Color(0xFF3E7A2E),
                Color(0xFF254117) //darker green
            ),
            center = Offset(w * 0.5f, h * 0.35f),
            radius = w * 0.32f
        )

        // Canopy
        drawCircle(
            brush = canopyGradient,
            radius = w * 0.32f,
            center = Offset(w * 0.5f, h * 0.35f)
        )
        drawCircle(
            color = Color(0xFF4B7B2D),
            radius = w * 0.22f,
            center = Offset(w * 0.35f, h * 0.4f)
        )
        drawCircle(
            color = Color(0xFF4B7B2D),
            radius = w * 0.22f,
            center = Offset(w * 0.65f, h * 0.4f)
        )

        // Trunk
        drawRoundRect(
            color = Color(0xFF3E5622),
            topLeft = Offset(w * 0.46f, h * 0.45f),
            size = Size(w * 0.08f, h * 0.35f),
            cornerRadius = CornerRadius(8f, 8f)
        )
    }
}

