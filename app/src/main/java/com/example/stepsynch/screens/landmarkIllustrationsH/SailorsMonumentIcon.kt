package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

@Composable
fun SailorsMonumentIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Base shadow ---
        drawRoundRect(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.4f, h * 0.78f),
            size = Size(w * 0.2f, h * 0.12f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // --- Monument gradient ---
        val monumentGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF9E9E9E),
                Color(0xFFBDBDBD),
                Color(0xFFE0E0E0)
            )
        )

        drawRoundRect(
            brush = monumentGradient,
            topLeft = Offset(w * 0.42f, h * 0.25f),
            size = Size(w * 0.16f, h * 0.53f),
            cornerRadius = CornerRadius(24f, 24f)
        )

        // --- Nautical anchor motif ---
        drawCircle(
            color = Color(0xFF5A5A5A),
            radius = w * 0.04f,
            center = Offset(w * 0.5f, h * 0.45f)
        )
        drawRect(
            color = Color(0xFF5A5A5A),
            topLeft = Offset(w * 0.49f, h * 0.45f),
            size = Size(w * 0.02f, h * 0.1f)
        )

        // --- Subtle highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.46f, h * 0.3f),
            size = Size(w * 0.06f, h * 0.4f),
            cornerRadius = CornerRadius(12f, 12f)
        )
    }
}