package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun StarflowerMeadowIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Meadow base ---
        drawOval(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.2f, h * 0.55f),
            size = Size(w * 0.6f, h * 0.25f)
        )

        // --- Flowers ---
        val flowerPositions = listOf(
            Offset(w * 0.35f, h * 0.6f),
            Offset(w * 0.5f, h * 0.58f),
            Offset(w * 0.62f, h * 0.63f),
            Offset(w * 0.45f, h * 0.68f)
        )

        flowerPositions.forEach {
            drawCircle(
                color = Color.White.copy(alpha = 0.8f),
                radius = w * 0.022f,
                center = it
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.2f),
                radius = w * 0.05f,
                center = it
            )
        }
    }
}