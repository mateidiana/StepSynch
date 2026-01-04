package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun WindCarvedRidgeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Back ridge shadow ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.1f, h * 0.65f)
                lineTo(w * 0.5f, h * 0.3f)
                lineTo(w * 0.9f, h * 0.7f)
                close()
            },
            color = Color.Black.copy(alpha = 0.25f)
        )

        // --- Main ridge gradient ---
        val ridgeGradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF4E4E4E),
                Color(0xFF7A7A7A),
                Color(0xFFA0A0A0)
            ),
            start = Offset(w * 0.5f, h * 0.3f),
            end = Offset(w * 0.5f, h)
        )

        drawPath(
            path = Path().apply {
                moveTo(w * 0.15f, h * 0.6f)
                lineTo(w * 0.5f, h * 0.25f)
                lineTo(w * 0.85f, h * 0.65f)
                close()
            },
            brush = ridgeGradient
        )

        // --- Wind highlight ---
        drawLine(
            color = Color.White.copy(alpha = 0.18f),
            start = Offset(w * 0.5f, h * 0.25f),
            end = Offset(w * 0.78f, h * 0.6f),
            strokeWidth = 2f
        )
    }
}