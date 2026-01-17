package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun HighCampIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Snow base shadow ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.25f, h * 0.75f),
            size = Size(w * 0.5f, h * 0.1f)
        )

        // --- Tent 1 ---
        drawPath(
            path = androidx.compose.ui.graphics.Path().apply {
                moveTo(w * 0.3f, h * 0.6f)
                lineTo(w * 0.4f, h * 0.75f)
                lineTo(w * 0.2f, h * 0.75f)
                close()
            },
            color = Color(0xFF8D1F1F)
        )

        // --- Tent 2 ---
        drawPath(
            path = androidx.compose.ui.graphics.Path().apply {
                moveTo(w * 0.6f, h * 0.55f)
                lineTo(w * 0.7f, h * 0.75f)
                lineTo(w * 0.5f, h * 0.75f)
                close()
            },
            color = Color(0xFFB23A3A)
        )

        // --- Tent highlights ---
        drawLine(
            color = Color.White.copy(alpha = 0.2f),
            start = Offset(w * 0.3f, h * 0.6f),
            end = Offset(w * 0.2f, h * 0.75f),
            strokeWidth = 1.5f
        )
        drawLine(
            color = Color.White.copy(alpha = 0.2f),
            start = Offset(w * 0.6f, h * 0.55f),
            end = Offset(w * 0.5f, h * 0.75f),
            strokeWidth = 1.5f
        )

        // --- Flag line ---
        drawLine(
            color = Color.Yellow.copy(alpha = 0.7f),
            start = Offset(w * 0.65f, h * 0.55f),
            end = Offset(w * 0.65f, h * 0.5f),
            strokeWidth = 2f
        )
    }
}