package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun SkyOrchardIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Floating soil base ---
        drawOval(
            color = Color(0xFF8D6E63),
            topLeft = Offset(w * 0.3f, h * 0.55f),
            size = Size(w * 0.4f, h * 0.18f)
        )

        // --- Trunk ---
        drawRect(
            color = Color(0xFF6D4C41),
            topLeft = Offset(w * 0.47f, h * 0.35f),
            size = Size(w * 0.06f, h * 0.22f)
        )

        // --- Canopy ---
        drawCircle(
            color = color.copy(alpha = 0.9f),
            radius = w * 0.18f,
            center = Offset(w * 0.5f, h * 0.28f)
        )

        // --- Fruit ---
        drawCircle(
            color = Color(0xFFFF7043),
            radius = w * 0.025f,
            center = Offset(w * 0.45f, h * 0.3f)
        )
        drawCircle(
            color = Color(0xFFFF7043),
            radius = w * 0.025f,
            center = Offset(w * 0.55f, h * 0.33f)
        )

        // --- Highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.12f),
            radius = w * 0.12f,
            center = Offset(w * 0.46f, h * 0.22f)
        )
    }
}