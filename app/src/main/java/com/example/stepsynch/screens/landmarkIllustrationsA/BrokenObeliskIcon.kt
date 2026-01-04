package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun BrokenObeliskIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Stone gradient ---
        val obeliskGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB7AFA2),
                Color(0xFF8E877B),
                Color(0xFF6A645B)
            )
        )

        // --- Shadow base ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.3f, h * 0.78f),
            size = Size(w * 0.4f, h * 0.12f)
        )

        // --- Main obelisk fragment ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.45f, h * 0.1f)
                lineTo(w * 0.65f, h * 0.75f)
                lineTo(w * 0.4f, h * 0.75f)
                close()
            },
            brush = obeliskGradient
        )

        // --- Fracture edge ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.45f, h * 0.1f)
                lineTo(w * 0.55f, h * 0.2f)
                lineTo(w * 0.48f, h * 0.22f)
                close()
            },
            color = Color(0xFF5E594F)
        )

        // --- Carved lines ---
        drawLine(
            color = Color.Black.copy(alpha = 0.25f),
            start = Offset(w * 0.52f, h * 0.3f),
            end = Offset(w * 0.52f, h * 0.6f),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.2f),
            start = Offset(w * 0.56f, h * 0.35f),
            end = Offset(w * 0.56f, h * 0.55f),
            strokeWidth = 1.5f
        )

        // --- Moss growth ---
        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.44f, h * 0.48f),
            size = Size(w * 0.1f, h * 0.08f)
        )

        // --- Subtle highlight ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.48f, h * 0.2f)
                lineTo(w * 0.6f, h * 0.75f)
                lineTo(w * 0.56f, h * 0.75f)
                close()
            },
            color = Color.White.copy(alpha = 0.06f)
        )
    }
}