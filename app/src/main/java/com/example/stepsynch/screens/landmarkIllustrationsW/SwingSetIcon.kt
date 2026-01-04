package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun SwingSetIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Shadow base (larger) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.25f, h * 0.72f),
            size = androidx.compose.ui.geometry.Size(w * 0.5f, h * 0.1f)
        )

        // --- Swing frame (taller and wider) ---
        drawLine(
            color = Color(0xFF8B5E3C),
            start = Offset(w * 0.32f, h * 0.35f),
            end = Offset(w * 0.32f, h * 0.7f),
            strokeWidth = 5f
        )
        drawLine(
            color = Color(0xFF8B5E3C),
            start = Offset(w * 0.68f, h * 0.35f),
            end = Offset(w * 0.68f, h * 0.7f),
            strokeWidth = 5f
        )
        drawLine(
            color = Color(0xFF8B5E3C),
            start = Offset(w * 0.32f, h * 0.35f),
            end = Offset(w * 0.68f, h * 0.35f),
            strokeWidth = 5f
        )

        // --- Swings (longer) ---
        drawLine(
            color = Color(0xFF6A4328),
            start = Offset(w * 0.38f, h * 0.35f),
            end = Offset(w * 0.38f, h * 0.68f),
            strokeWidth = 3f
        )
        drawLine(
            color = Color(0xFF6A4328),
            start = Offset(w * 0.62f, h * 0.35f),
            end = Offset(w * 0.62f, h * 0.68f),
            strokeWidth = 3f
        )

        // --- Swing seats (larger) ---
        drawRect(
            color = Color(0xFFB23A3A),
            topLeft = Offset(w * 0.36f, h * 0.68f),
            size = androidx.compose.ui.geometry.Size(w * 0.05f, h * 0.035f)
        )
        drawRect(
            color = Color(0xFFB23A3A),
            topLeft = Offset(w * 0.59f, h * 0.68f),
            size = androidx.compose.ui.geometry.Size(w * 0.05f, h * 0.035f)
        )
    }
}
