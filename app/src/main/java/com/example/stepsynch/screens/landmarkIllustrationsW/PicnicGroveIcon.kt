package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun PicnicGroveIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground shadow (larger) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.15f, h * 0.68f),
            size = Size(w * 0.7f, h * 0.12f)
        )

        // --- Table (larger) ---
        drawRoundRect(
            color = Color(0xFF8B5E3C),
            topLeft = Offset(w * 0.3f, h * 0.52f),
            size = Size(w * 0.4f, h * 0.06f),
            cornerRadius = CornerRadius(5f, 5f)
        )

        // --- Table legs (taller, wider spacing) ---
        drawRect(
            color = Color(0xFF6A4328),
            topLeft = Offset(w * 0.31f, h * 0.58f),
            size = Size(w * 0.025f, h * 0.12f)
        )
        drawRect(
            color = Color(0xFF6A4328),
            topLeft = Offset(w * 0.64f, h * 0.58f),
            size = Size(w * 0.025f, h * 0.12f)
        )

        // --- Bench 1 (larger, slightly higher) ---
        drawRoundRect(
            color = Color(0xFF7A4F34),
            topLeft = Offset(w * 0.25f, h * 0.58f),
            size = Size(w * 0.22f, h * 0.04f),
            cornerRadius = CornerRadius(4f, 4f)
        )

        // --- Bench 2 (larger, slightly higher) ---
        drawRoundRect(
            color = Color(0xFF7A4F34),
            topLeft = Offset(w * 0.53f, h * 0.58f),
            size = Size(w * 0.22f, h * 0.04f),
            cornerRadius = CornerRadius(4f, 4f)
        )
    }
}
