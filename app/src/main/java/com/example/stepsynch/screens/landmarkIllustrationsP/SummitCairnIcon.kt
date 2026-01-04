package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun SummitCairnIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground shadow ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.32f, h * 0.78f),
            size = Size(w * 0.36f, h * 0.12f)
        )

        // --- Base stone ---
        drawRoundRect(
            color = Color(0xFF6E6E6E),
            topLeft = Offset(w * 0.28f, h * 0.65f),
            size = Size(w * 0.44f, h * 0.14f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // --- Middle stone ---
        drawRoundRect(
            color = Color(0xFF7A7A7A),
            topLeft = Offset(w * 0.34f, h * 0.52f),
            size = Size(w * 0.32f, h * 0.14f),
            cornerRadius = CornerRadius(16f, 16f)
        )

        // --- Top stone ---
        drawRoundRect(
            color = Color(0xFF8A8A8A),
            topLeft = Offset(w * 0.42f, h * 0.4f),
            size = Size(w * 0.16f, h * 0.12f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // --- Snow highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.2f),
            topLeft = Offset(w * 0.44f, h * 0.4f),
            size = Size(w * 0.12f, h * 0.04f),
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
}