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
fun FernHollowIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Shadowed hollow base ---
        drawCircle(
            color = Color.Black.copy(alpha = 0.35f),
            radius = w * 0.32f,
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // --- Fern frond gradient ---
        val fernGradient = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = 0.95f),
                Color(0xFF709255),
                Color(0xFF3E5622)
            ),
            center = Offset(w * 0.5f, h * 0.45f),
            radius = w * 0.5f
        )

        // --- Fern fronds (layered fans) ---
        drawOval(
            brush = fernGradient,
            topLeft = Offset(w * 0.18f, h * 0.28f),
            size = Size(w * 0.26f, h * 0.42f)
        )
        drawOval(
            brush = fernGradient,
            topLeft = Offset(w * 0.56f, h * 0.28f),
            size = Size(w * 0.26f, h * 0.42f)
        )
        drawOval(
            brush = fernGradient,
            topLeft = Offset(w * 0.34f, h * 0.22f),
            size = Size(w * 0.32f, h * 0.48f)
        )

        // --- Center highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.1f),
            radius = w * 0.14f,
            center = Offset(w * 0.5f, h * 0.45f)
        )
    }
}