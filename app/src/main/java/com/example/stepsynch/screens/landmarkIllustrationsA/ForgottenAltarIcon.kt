package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun ForgottenAltarIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Altar top slab ---
        val slabGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB8B0A2),
                Color(0xFF8C8579)
            )
        )

        drawRoundRect(
            brush = slabGradient,
            topLeft = Offset(w * 0.22f, h * 0.35f),
            size = Size(w * 0.56f, h * 0.18f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(14f, 14f)
        )

        // --- Altar base ---
        drawRoundRect(
            color = Color(0xFF6E685E),
            topLeft = Offset(w * 0.32f, h * 0.55f),
            size = Size(w * 0.36f, h * 0.18f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )

        // --- Cracks on slab ---
        drawLine(
            color = Color.Black.copy(alpha = 0.25f),
            start = Offset(w * 0.35f, h * 0.42f),
            end = Offset(w * 0.55f, h * 0.48f),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Black.copy(alpha = 0.2f),
            start = Offset(w * 0.5f, h * 0.38f),
            end = Offset(w * 0.48f, h * 0.5f),
            strokeWidth = 1.5f
        )

        // --- Moss accumulation ---
        drawOval(
            color = color.copy(alpha = 0.4f),
            topLeft = Offset(w * 0.26f, h * 0.46f),
            size = Size(w * 0.12f, h * 0.08f)
        )

        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.6f, h * 0.5f),
            size = Size(w * 0.1f, h * 0.07f)
        )

        // --- Offering depression highlight ---
        drawOval(
            color = Color.Black.copy(alpha = 0.2f),
            topLeft = Offset(w * 0.45f, h * 0.4f),
            size = Size(w * 0.1f, h * 0.06f)
        )

        // --- Light wear highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.26f, h * 0.36f),
            size = Size(w * 0.2f, h * 0.05f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
        )
    }
}