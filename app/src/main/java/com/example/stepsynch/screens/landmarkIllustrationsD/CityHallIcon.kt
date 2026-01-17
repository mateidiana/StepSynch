package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path


@Composable
fun CityHallIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Roof
        drawPath(
            path = Path().apply {
                moveTo(w * 0.5f, h * 0.12f)
                lineTo(w * 0.9f, h * 0.32f)
                lineTo(w * 0.1f, h * 0.32f)
                close()
            },
            color = color.copy(alpha = 0.9f)
        )

        // Building base
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.18f, h * 0.32f),
            size = Size(w * 0.64f, h * 0.45f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // Pillars
        val pillarWidth = w * 0.08f
        for (i in 0..2) {
            drawRoundRect(
                color = Color.White.copy(alpha = 0.2f),
                topLeft = Offset(w * (0.28f + i * 0.16f), h * 0.36f),
                size = Size(pillarWidth, h * 0.35f),
                cornerRadius = CornerRadius(6f, 6f)
            )
        }
    }
}
