package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun CoastalBreakwaterIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Water base ---
        drawRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(0f, h * 0.5f),
            size = Size(w, h * 0.5f)
        )

        // --- Layered stones ---
        val stoneColors = listOf(
            Color(0xFF7A7A7A),
            Color(0xFF6A6A6A),
            Color(0xFF8D8D8D)
        )
        listOf(0.05f, 0.35f, 0.65f).forEachIndexed { index, x ->
            drawRoundRect(
                color = stoneColors[index],
                topLeft = Offset(w * x, h * 0.55f),
                size = Size(w * 0.3f, h * 0.1f),
                cornerRadius = CornerRadius(20f, 20f)
            )
        }

        // --- Foam highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.1f, h * 0.58f),
            size = Size(w * 0.8f, h * 0.06f),
            cornerRadius = CornerRadius(12f, 12f)
        )
    }
}