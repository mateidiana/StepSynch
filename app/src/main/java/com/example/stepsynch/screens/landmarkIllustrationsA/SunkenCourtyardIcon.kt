package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun SunkenCourtyardIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Upper ground level ---
        drawRect(
            color = Color(0xFFB3AB9D),
            topLeft = Offset(w * 0.15f, h * 0.15f),
            size = Size(w * 0.7f, h * 0.2f)
        )

        // --- Lower recessed floor ---
        val floorGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF7E786E),
                Color(0xFF5F5A52)
            )
        )

        drawRect(
            brush = floorGradient,
            topLeft = Offset(w * 0.25f, h * 0.4f),
            size = Size(w * 0.5f, h * 0.35f)
        )

        // --- Step shadows ---
        drawRect(
            color = Color.Black.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.25f, h * 0.4f),
            size = Size(w * 0.5f, h * 0.05f)
        )

        // --- Cracks ---
        drawLine(
            color = Color.Black.copy(alpha = 0.25f),
            start = Offset(w * 0.35f, h * 0.52f),
            end = Offset(w * 0.48f, h * 0.68f),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.2f),
            start = Offset(w * 0.55f, h * 0.5f),
            end = Offset(w * 0.6f, h * 0.72f),
            strokeWidth = 1.5f
        )

        // --- Moss creeping in ---
        drawOval(
            color = color.copy(alpha = 0.4f),
            topLeft = Offset(w * 0.28f, h * 0.45f),
            size = Size(w * 0.12f, h * 0.08f)
        )

        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.55f, h * 0.62f),
            size = Size(w * 0.14f, h * 0.1f)
        )

        // --- Subtle light falloff ---
        drawRect(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.25f, h * 0.4f),
            size = Size(w * 0.5f, h * 0.15f)
        )
    }
}