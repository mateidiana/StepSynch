package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun CoffeeShopIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Cup
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.3f, h * 0.4f),
            size = Size(w * 0.4f, h * 0.28f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // Handle
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.58f, h * 0.44f),
            size = Size(w * 0.2f, h * 0.2f),
            style = Stroke(width = w * 0.05f)
        )

        // Steam lines
        drawLine(
            color = Color.White.copy(alpha = 0.35f),
            start = Offset(w * 0.42f, h * 0.28f),
            end = Offset(w * 0.42f, h * 0.18f),
            strokeWidth = 3f
        )
        drawLine(
            color = Color.White.copy(alpha = 0.35f),
            start = Offset(w * 0.52f, h * 0.28f),
            end = Offset(w * 0.52f, h * 0.16f),
            strokeWidth = 3f
        )
    }
}
