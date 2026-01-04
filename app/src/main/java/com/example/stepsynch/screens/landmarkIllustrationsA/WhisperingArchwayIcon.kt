package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun WhisperingArchwayIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Arch outer shape ---
        drawArc(
            color = Color(0xFF9A9286),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.2f, h * 0.25f),
            size = Size(w * 0.6f, h * 0.5f),
            style = Stroke(width = w * 0.14f)
        )

        // --- Inner shadow (depth) ---
        drawArc(
            color = Color.Black.copy(alpha = 0.35f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.3f, h * 0.32f),
            size = Size(w * 0.4f, h * 0.36f),
            style = Stroke(width = w * 0.12f)
        )

        // --- Broken stone chips ---
        drawCircle(
            color = Color(0xFF6E685E),
            radius = w * 0.035f,
            center = Offset(w * 0.28f, h * 0.48f)
        )

        drawCircle(
            color = Color(0xFF6E685E),
            radius = w * 0.03f,
            center = Offset(w * 0.72f, h * 0.5f)
        )

        // --- Moss accents ---
        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.24f, h * 0.44f),
            size = Size(w * 0.12f, h * 0.1f)
        )

        drawOval(
            color = color.copy(alpha = 0.3f),
            topLeft = Offset(w * 0.62f, h * 0.5f),
            size = Size(w * 0.12f, h * 0.1f)
        )

        // --- Subtle edge highlight ---
        drawArc(
            color = Color.White.copy(alpha = 0.06f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.22f, h * 0.25f),
            size = Size(w * 0.56f, h * 0.48f),
            style = Stroke(width = w * 0.04f)
        )
    }
}