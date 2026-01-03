package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

@Composable
fun MossyClearingIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground base gradient ---
        val groundGradient = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = 0.95f),
                color.copy(alpha = 0.75f),
                Color(0xFF3E5622)
            ),
            center = Offset(w * 0.5f, h * 0.6f),
            radius = w * 0.6f
        )

        // --- Main clearing mound ---
        drawOval(
            brush = groundGradient,
            topLeft = Offset(w * 0.1f, h * 0.35f),
            size = Size(w * 0.8f, h * 0.45f)
        )

        // --- Moss texture blobs ---
        drawCircle(
            color = Color(0xFF95B46A).copy(alpha = 0.5f),
            radius = w * 0.12f,
            center = Offset(w * 0.35f, h * 0.55f)
        )
        drawCircle(
            color = Color(0xFF709255).copy(alpha = 0.45f),
            radius = w * 0.1f,
            center = Offset(w * 0.55f, h * 0.6f)
        )
        drawCircle(
            color = Color(0xFF95B46A).copy(alpha = 0.35f),
            radius = w * 0.08f,
            center = Offset(w * 0.48f, h * 0.5f)
        )

        // --- Small stones ---
        drawOval(
            color = Color(0xFF5A5A5A).copy(alpha = 0.5f),
            topLeft = Offset(w * 0.28f, h * 0.68f),
            size = Size(w * 0.08f, h * 0.04f)
        )
        drawOval(
            color = Color(0xFF6A6A6A).copy(alpha = 0.45f),
            topLeft = Offset(w * 0.6f, h * 0.7f),
            size = Size(w * 0.06f, h * 0.035f)
        )

        // --- Soft highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.3f, h * 0.42f),
            size = Size(w * 0.4f, h * 0.18f)
        )
    }
}