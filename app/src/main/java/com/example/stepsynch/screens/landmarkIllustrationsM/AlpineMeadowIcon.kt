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
fun AlpineMeadowIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Back meadow hill ---
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.1f, h * 0.52f),
            size = Size(w * 0.8f, h * 0.22f),
            cornerRadius = CornerRadius(32f, 32f)
        )

        // --- Front meadow hill ---
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.15f, h * 0.58f),
            size = Size(w * 0.7f, h * 0.22f),
            cornerRadius = CornerRadius(28f, 28f)
        )

        // --- Flowers ---
        val flowerPositions = listOf(
            Offset(w * 0.4f, h * 0.55f),
            Offset(w * 0.52f, h * 0.52f),
            Offset(w * 0.6f, h * 0.57f)
        )

        flowerPositions.forEach {
            drawCircle(
                color = Color.White.copy(alpha = 0.8f),
                radius = w * 0.03f,
                center = it
            )

            drawCircle(
                color = Color(0xFFFFE082).copy(alpha = 0.9f), // soft yellow center
                radius = w * 0.012f,
                center = it
            )
        }

        // --- Sunlight highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.12f),
            radius = w * 0.18f,
            center = Offset(w * 0.45f, h * 0.48f)
        )
    }
}

