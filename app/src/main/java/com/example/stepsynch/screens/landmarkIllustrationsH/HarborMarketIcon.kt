package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun HarborMarketIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground / dock ---
        drawRect(
            color = Color(0xFF5A3A1A),
            topLeft = Offset(0f, h * 0.55f),
            size = Size(w, h * 0.45f)
        )

        // --- Canopies (triangular) ---
        val canopyColors = listOf(
            Color(0xFFBDBDBD),
            Color(0xFFAFAFAF),
            Color(0xFF9E9E9E)
        )
        listOf(0.1f, 0.35f, 0.6f).forEachIndexed { index, x ->
            drawRect(
                color = canopyColors[index],
                topLeft = Offset(w * x, h * 0.35f),
                size = Size(w * 0.25f, h * 0.2f)
            )
        }

        // --- Crates stacked ---
        drawRect(
            color = Color(0xFF795548),
            topLeft = Offset(w * 0.15f, h * 0.6f),
            size = Size(w * 0.06f, h * 0.08f)
        )
        drawRect(
            color = Color(0xFF6D4C41),
            topLeft = Offset(w * 0.22f, h * 0.62f),
            size = Size(w * 0.05f, h * 0.07f)
        )
        drawRect(
            color = Color(0xFF8D6E63),
            topLeft = Offset(w * 0.5f, h * 0.6f),
            size = Size(w * 0.06f, h * 0.08f)
        )

        // --- Highlight on stalls ---
        drawRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.35f, h * 0.38f),
            size = Size(w * 0.3f, h * 0.18f)
        )
    }
}