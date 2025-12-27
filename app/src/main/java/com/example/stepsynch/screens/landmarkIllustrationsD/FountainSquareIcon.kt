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
fun FountainSquareIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Outer plaza
        drawCircle(
            color = color.copy(alpha = 0.85f),
            radius = w * 0.4f,
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // Water pool
        drawCircle(
            color = Color(0xFF2F5D6B),
            radius = w * 0.28f,
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // Fountain jet
        drawRoundRect(
            color = Color.White.copy(alpha = 0.8f),
            topLeft = Offset(w * 0.48f, h * 0.2f),
            size = Size(w * 0.04f, h * 0.35f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // Splash highlight
        drawCircle(
            color = Color.White.copy(alpha = 0.2f),
            radius = w * 0.12f,
            center = Offset(w * 0.5f, h * 0.55f)
        )
    }
}
