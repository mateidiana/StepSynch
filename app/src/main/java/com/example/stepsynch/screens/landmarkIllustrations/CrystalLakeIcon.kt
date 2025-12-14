package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CrystalLakeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Lake gradient (deep center → lighter edges) ---
        val lakeGradient = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = 0.9f),
                color.copy(alpha = 0.65f),
                color.copy(alpha = 0.45f)
            ),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.55f
        )

        // --- Outer shore shadow ---
        drawOval(
            color = Color(0xFF2E3D2F).copy(alpha = 0.45f),
            topLeft = Offset(w * 0.08f, h * 0.12f),
            size = Size(w * 0.84f, h * 0.76f)
        )

        // --- Main lake body ---
        drawOval(
            brush = lakeGradient,
            topLeft = Offset(w * 0.12f, h * 0.16f),
            size = Size(w * 0.76f, h * 0.68f)
        )

        // --- Shallow edge highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.18f, h * 0.22f),
            size = Size(w * 0.64f, h * 0.56f)
        )

        // --- Crystal sparkle highlights ---
        drawCircle(
            color = Color.White.copy(alpha = 0.35f),
            radius = w * 0.035f,
            center = Offset(w * 0.42f, h * 0.45f)
        )

        drawCircle(
            color = Color.White.copy(alpha = 0.25f),
            radius = w * 0.025f,
            center = Offset(w * 0.6f, h * 0.52f)
        )

        drawCircle(
            color = Color.White.copy(alpha = 0.18f),
            radius = w * 0.02f,
            center = Offset(w * 0.5f, h * 0.62f)
        )

        // --- Gentle ripple lines ---
        drawArc(
            color = Color.White.copy(alpha = 0.12f),
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(w * 0.28f, h * 0.38f),
            size = Size(w * 0.32f, h * 0.18f),
            style = Stroke(width = 2f)
        )

        drawArc(
            color = Color.White.copy(alpha = 0.1f),
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(w * 0.34f, h * 0.48f),
            size = Size(w * 0.28f, h * 0.14f),
            style = Stroke(width = 1.5f)
        )
    }
}

