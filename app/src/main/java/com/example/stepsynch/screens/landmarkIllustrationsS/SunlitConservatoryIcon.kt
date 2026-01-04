package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun SunlitConservatoryIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Glass body ---
        val glassGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFE3F2FD).copy(alpha = 0.9f),
                Color(0xFFBBDEFB).copy(alpha = 0.7f)
            )
        )

        drawRect(
            brush = glassGradient,
            topLeft = Offset(w * 0.28f, h * 0.35f),
            size = Size(w * 0.44f, h * 0.35f)
        )

        // --- Roof ---
        drawRect(
            color = Color(0xFF90CAF9),
            topLeft = Offset(w * 0.25f, h * 0.3f),
            size = Size(w * 0.5f, h * 0.07f)
        )

        // --- Interior greenery ---
        drawRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.32f, h * 0.55f),
            size = Size(w * 0.3f, h * 0.12f)
        )

        // --- Light reflection ---
        drawRect(
            color = Color.White.copy(alpha = 0.14f),
            topLeft = Offset(w * 0.32f, h * 0.38f),
            size = Size(w * 0.08f, h * 0.28f)
        )
    }
}