package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun FishingBoatsIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Water base ---
        drawOval(
            color = color.copy(alpha = 0.65f),
            topLeft = Offset(w * 0.1f, h * 0.45f),
            size = Size(w * 0.8f, h * 0.4f)
        )

        // --- Back boat ---
        drawOval(
            color = Color(0xFF5A3A1A),
            topLeft = Offset(w * 0.28f, h * 0.48f),
            size = Size(w * 0.28f, h * 0.12f)
        )

        // --- Front boat ---
        drawOval(
            color = Color(0xFF6D4C2F),
            topLeft = Offset(w * 0.42f, h * 0.55f),
            size = Size(w * 0.3f, h * 0.14f)
        )

        // --- Boat highlights ---
        drawOval(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.46f, h * 0.57f),
            size = Size(w * 0.18f, h * 0.06f)
        )

        // --- Gentle ripples ---
        drawOval(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.32f, h * 0.7f),
            size = Size(w * 0.36f, h * 0.08f)
        )
    }
}