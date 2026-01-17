package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius

@Composable
fun SunbeamGladeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground clearing ---
        drawOval(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.18f, h * 0.55f),
            size = Size(w * 0.64f, h * 0.3f)
        )

        // --- Sunbeam gradient ---
        val beamGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFFFF3C0).copy(alpha = 0.45f),
                Color(0xFFFFF3C0).copy(alpha = 0.15f),
                Color.Transparent
            )
        )

        // --- Light rays ---
        drawRect(
            brush = beamGradient,
            topLeft = Offset(w * 0.35f, h * 0.05f),
            size = Size(w * 0.12f, h * 0.5f)
        )
        drawRect(
            brush = beamGradient,
            topLeft = Offset(w * 0.53f, h * 0.1f),
            size = Size(w * 0.1f, h * 0.45f)
        )

        // --- Warm ground highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.18f),
            topLeft = Offset(w * 0.32f, h * 0.58f),
            size = Size(w * 0.36f, h * 0.18f)
        )
    }
}