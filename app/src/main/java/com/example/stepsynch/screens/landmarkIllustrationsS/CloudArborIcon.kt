package com.example.stepsynch.screens.landmarkIllustrationsS

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CloudArborIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Clouds ---
        drawCircle(
            color = Color(0xFFECEFF1),
            radius = w * 0.18f,
            center = Offset(w * 0.35f, h * 0.55f)
        )
        drawCircle(
            color = Color(0xFFF5F7F8),
            radius = w * 0.2f,
            center = Offset(w * 0.55f, h * 0.55f)
        )

        // --- Arbor arch ---
        drawArc(
            color = color.copy(alpha = 0.85f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.28f, h * 0.3f),
            size = Size(w * 0.44f, h * 0.5f),
            style = Stroke(width = w * 0.05f)
        )

        // --- Vines ---
        drawCircle(
            color = color.copy(alpha = 0.7f),
            radius = w * 0.04f,
            center = Offset(w * 0.32f, h * 0.42f)
        )
        drawCircle(
            color = color.copy(alpha = 0.6f),
            radius = w * 0.035f,
            center = Offset(w * 0.68f, h * 0.44f)
        )

        // --- Soft glow ---
        drawCircle(
            color = Color.White.copy(alpha = 0.12f),
            radius = w * 0.22f,
            center = Offset(w * 0.5f, h * 0.45f)
        )
    }
}