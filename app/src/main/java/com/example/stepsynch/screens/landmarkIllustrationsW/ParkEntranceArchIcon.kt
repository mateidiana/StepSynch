package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun ParkEntranceArchIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Shadow base (larger) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.25f, h * 0.7f),
            size = Size(w * 0.5f, h * 0.12f)
        )

        // --- Arch pillars (larger, taller) ---
        drawRoundRect(
            color = Color(0xFF8B5E3C),
            topLeft = Offset(w * 0.3f, h * 0.42f),
            size = Size(w * 0.1f, h * 0.34f),
            cornerRadius = CornerRadius(5f, 5f)
        )
        drawRoundRect(
            color = Color(0xFF8B5E3C),
            topLeft = Offset(w * 0.6f, h * 0.42f),
            size = Size(w * 0.1f, h * 0.34f),
            cornerRadius = CornerRadius(5f, 5f)
        )

        // --- Arch top (wider and taller) ---
        drawRoundRect(
            color = Color(0xFFB23A3A),
            topLeft = Offset(w * 0.3f, h * 0.38f),
            size = Size(w * 0.4f, h * 0.08f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // --- Highlight on arch (adjusted to match new size) ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.3f, h * 0.38f),
            size = Size(w * 0.4f, h * 0.03f),
            cornerRadius = CornerRadius(14f, 14f)
        )
    }
}
