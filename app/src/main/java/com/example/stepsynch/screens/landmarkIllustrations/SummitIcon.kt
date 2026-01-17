package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun SummitIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Back mountain (shadow layer) ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.55f, h * 0.05f)
                lineTo(w * 0.95f, h)
                lineTo(w * 0.2f, h)
                close()
            },
            color = Color.Black.copy(alpha = 0.25f)
        )

        // --- Gradient for main mountain body ---
        val mountainBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF2B1A0D), // very dark brown (bottom/inside)
                Color(0xFF5A3A1A), // medium brown
                Color(0xFFB08B4F)  // light brown (top/outside)
            ),
            start = Offset(w * 0.5f, 0f),       // top of mountain
            end = Offset(w * 0.5f, h)          // bottom of mountain
        )

        // --- Main mountain body with gradient ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.5f, 0f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            },
            brush = mountainBrush
        )

        // --- Rock face highlight ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.5f, h * 0.08f)
                lineTo(w * 0.78f, h)
                lineTo(w * 0.5f, h)
                close()
            },
            color = Color(0xFF5A3A1A).copy(alpha = 0.85f)
        )

        // --- Snowcap ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.5f, h * 0.08f)
                lineTo(w * 0.6f, h * 0.22f)
                lineTo(w * 0.4f, h * 0.22f)
                close()
            },
            color = Color.White.copy(alpha = 0.85f)
        )

        // --- Subtle ridge line ---
        drawLine(
            color = Color.White.copy(alpha = 0.12f),
            start = Offset(w * 0.5f, h * 0.22f),
            end = Offset(w * 0.72f, h * 0.85f),
            strokeWidth = 2f
        )
    }
}
