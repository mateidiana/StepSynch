package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun ObservationDeckIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Deck shadow (larger) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.2f, h * 0.72f),
            size = Size(w * 0.6f, h * 0.12f)
        )

        // --- Deck platform (larger and taller) ---
        drawRect(
            color = Color(0xFF8B5E3C),
            topLeft = Offset(w * 0.25f, h * 0.55f),
            size = Size(w * 0.5f, h * 0.1f)
        )

        // --- Railings (proportional, larger) ---
        drawLine(
            color = Color(0xFF6A4328),
            start = Offset(w * 0.25f, h * 0.55f),
            end = Offset(w * 0.25f, h * 0.65f),
            strokeWidth = 3f
        )
        drawLine(
            color = Color(0xFF6A4328),
            start = Offset(w * 0.75f, h * 0.55f),
            end = Offset(w * 0.75f, h * 0.65f),
            strokeWidth = 3f
        )
        drawLine(
            color = Color(0xFF6A4328),
            start = Offset(w * 0.25f, h * 0.55f),
            end = Offset(w * 0.75f, h * 0.55f),
            strokeWidth = 3f
        )
    }
}
