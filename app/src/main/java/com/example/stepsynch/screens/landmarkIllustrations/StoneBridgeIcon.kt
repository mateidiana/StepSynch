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
fun StoneBridgeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Shadow arch (slightly below and darker)
        drawArc(
            color = Color.Black.copy(alpha = 0.25f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.1f, h * 0.35f),
            size = Size(w * 0.8f, h * 0.5f),
            style = Stroke(width = w * 0.14f)
        )

        // Gradient for the main arch
        val archBrush = Brush.radialGradient(
            colors = listOf(
                //Color(0xFF000000), // black outside
                Color(0xFF2B1A0D), // very dark brown inside
                Color(0xFF5A3A1A),
                Color(0xFFB08B4F)// medium brown
            ),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.45f
        )

        // Main arch with gradient
        drawArc(
            brush = archBrush,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.1f, h * 0.32f),
            size = Size(w * 0.8f, h * 0.5f),
            style = Stroke(width = w * 0.12f)
        )

        // Optional: subtle base line for bridge
        drawLine(
            color = Color(0xFF3E5622).copy(alpha = 0.6f),
            start = Offset(w * 0.15f, h * 0.57f),
            end = Offset(w * 0.85f, h * 0.57f),
            strokeWidth = 2f
        )
    }
}
