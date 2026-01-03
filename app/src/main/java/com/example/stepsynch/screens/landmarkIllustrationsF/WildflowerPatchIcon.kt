package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius

@Composable
fun WildflowerPatchIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground base ---
        drawOval(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.18f, h * 0.55f),
            size = Size(w * 0.64f, h * 0.3f)
        )

        // --- Flowers ---
        val flowerColors = listOf(
            Color(0xFFFFE082),
            Color(0xFFCE93D8),
            Color(0xFFFFAB91),
            Color.White
        )

        flowerColors.forEachIndexed { index, flowerColor ->
            drawCircle(
                color = flowerColor.copy(alpha = 0.85f),
                radius = w * 0.04f,
                center = Offset(
                    w * (0.35f + index * 0.08f),
                    h * (0.6f + (index % 2) * 0.05f)
                )
            )
        }

        // --- Subtle highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.1f),
            topLeft = Offset(w * 0.3f, h * 0.58f),
            size = Size(w * 0.4f, h * 0.16f)
        )
    }
}