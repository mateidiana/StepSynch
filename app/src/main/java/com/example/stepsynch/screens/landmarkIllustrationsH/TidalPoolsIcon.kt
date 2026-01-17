package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun TidalPoolsIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground / rocks ---
        drawOval(
            color = Color(0xFF7A7A7A),
            topLeft = Offset(w * 0.1f, h * 0.55f),
            size = Size(w * 0.3f, h * 0.25f)
        )
        drawOval(
            color = Color(0xFF8D8D8D),
            topLeft = Offset(w * 0.45f, h * 0.55f),
            size = Size(w * 0.25f, h * 0.2f)
        )
        drawOval(
            color = Color(0xFF6A6A6A),
            topLeft = Offset(w * 0.7f, h * 0.58f),
            size = Size(w * 0.2f, h * 0.18f)
        )

        // --- Water pools ---
        drawOval(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.12f, h * 0.57f),
            size = Size(w * 0.26f, h * 0.12f)
        )
        drawOval(
            color = color.copy(alpha = 0.5f),
            topLeft = Offset(w * 0.48f, h * 0.58f),
            size = Size(w * 0.22f, h * 0.10f)
        )
        drawOval(
            color = color.copy(alpha = 0.45f),
            topLeft = Offset(w * 0.72f, h * 0.6f),
            size = Size(w * 0.16f, h * 0.08f)
        )

        // --- Gentle water highlights ---
        drawOval(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.15f, h * 0.58f),
            size = Size(w * 0.22f, h * 0.06f)
        )
        drawOval(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.5f, h * 0.59f),
            size = Size(w * 0.18f, h * 0.05f)
        )
    }
}