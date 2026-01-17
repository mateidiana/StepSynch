package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun SummitCrossIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Shadow base ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.45f, h * 0.78f),
            size = androidx.compose.ui.geometry.Size(w * 0.1f, h * 0.05f)
        )

        // --- Vertical post ---
        drawLine(
            color = Color(0xFF7A7A7A),
            start = Offset(w * 0.5f, h * 0.3f),
            end = Offset(w * 0.5f, h * 0.75f),
            strokeWidth = w * 0.06f
        )

        // --- Horizontal cross ---
        drawLine(
            color = Color(0xFF7A7A7A),
            start = Offset(w * 0.42f, h * 0.45f),
            end = Offset(w * 0.58f, h * 0.45f),
            strokeWidth = w * 0.05f
        )

        // --- Highlight on post ---
        drawLine(
            color = Color.White.copy(alpha = 0.15f),
            start = Offset(w * 0.5f, h * 0.3f),
            end = Offset(w * 0.5f, h * 0.45f),
            strokeWidth = w * 0.02f
        )
    }
}