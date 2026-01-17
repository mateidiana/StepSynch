package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun WindChimeGroveIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Branch ---
        drawRoundRect(
            color = color.copy(alpha = 0.85f),
            topLeft = Offset(w * 0.2f, h * 0.25f),
            size = Size(w * 0.6f, h * 0.05f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f)
        )

        // --- Chime strings ---
        drawRect(
            color = Color.White.copy(alpha = 0.4f),
            topLeft = Offset(w * 0.35f, h * 0.3f),
            size = Size(w * 0.01f, h * 0.35f)
        )
        drawRect(
            color = Color.White.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.5f, h * 0.3f),
            size = Size(w * 0.01f, h * 0.4f)
        )
        drawRect(
            color = Color.White.copy(alpha = 0.3f),
            topLeft = Offset(w * 0.65f, h * 0.3f),
            size = Size(w * 0.01f, h * 0.32f)
        )

        // --- Chimes ---
        drawRoundRect(
            color = Color(0xFFCFD8DC),
            topLeft = Offset(w * 0.32f, h * 0.62f),
            size = Size(w * 0.06f, h * 0.14f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
        )
        drawRoundRect(
            color = Color(0xFFB0BEC5),
            topLeft = Offset(w * 0.47f, h * 0.66f),
            size = Size(w * 0.06f, h * 0.16f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
        )
        drawRoundRect(
            color = Color(0xFF90A4AE),
            topLeft = Offset(w * 0.62f, h * 0.6f),
            size = Size(w * 0.06f, h * 0.13f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
        )
    }
}