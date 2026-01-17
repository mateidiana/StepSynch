package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun IcefallCliffsIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Cliff face ---
        val cliffGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF5E5E5E),
                Color(0xFF3F3F3F)
            )
        )

        drawRect(
            brush = cliffGradient,
            topLeft = Offset(w * 0.25f, h * 0.2f),
            size = Size(w * 0.5f, h * 0.6f)
        )

        // --- Ice streaks ---
        drawRect(
            color = Color(0xFFB3E5FC).copy(alpha = 0.9f),
            topLeft = Offset(w * 0.32f, h * 0.22f),
            size = Size(w * 0.06f, h * 0.5f)
        )

        drawRect(
            color = Color(0xFF81D4FA).copy(alpha = 0.85f),
            topLeft = Offset(w * 0.48f, h * 0.24f),
            size = Size(w * 0.05f, h * 0.46f)
        )

        drawRect(
            color = Color(0xFFB3E5FC).copy(alpha = 0.8f),
            topLeft = Offset(w * 0.6f, h * 0.26f),
            size = Size(w * 0.04f, h * 0.42f)
        )

        // --- Ice highlights ---
        drawRect(
            color = Color.White.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.32f, h * 0.22f),
            size = Size(w * 0.02f, h * 0.18f)
        )
    }
}