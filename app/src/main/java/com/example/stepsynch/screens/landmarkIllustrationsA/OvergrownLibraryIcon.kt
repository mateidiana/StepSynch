package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun OvergrownLibraryIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Back wall ---
        drawRoundRect(
            color = Color(0xFF9E978A),
            topLeft = Offset(w * 0.2f, h * 0.25f),
            size = Size(w * 0.6f, h * 0.3f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // --- Collapsed front wall ---
        drawRoundRect(
            color = Color(0xFF7A7468),
            topLeft = Offset(w * 0.28f, h * 0.48f),
            size = Size(w * 0.44f, h * 0.22f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // --- Broken arch hint ---
        drawArc(
            color = Color.Black.copy(alpha = 0.25f),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(w * 0.38f, h * 0.45f),
            size = Size(w * 0.24f, h * 0.18f),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 3f)
        )

        // --- Ivy / foliage ---
        drawOval(
            color = color.copy(alpha = 0.45f),
            topLeft = Offset(w * 0.22f, h * 0.28f),
            size = Size(w * 0.18f, h * 0.2f)
        )

        drawOval(
            color = color.copy(alpha = 0.4f),
            topLeft = Offset(w * 0.58f, h * 0.4f),
            size = Size(w * 0.18f, h * 0.22f)
        )

        // --- Fallen stones ---
        drawOval(
            color = Color(0xFF6A645B),
            topLeft = Offset(w * 0.32f, h * 0.7f),
            size = Size(w * 0.14f, h * 0.08f)
        )

        drawOval(
            color = Color(0xFF5E594F),
            topLeft = Offset(w * 0.55f, h * 0.72f),
            size = Size(w * 0.16f, h * 0.1f)
        )

        // --- Light wear highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.22f, h * 0.26f),
            size = Size(w * 0.18f, h * 0.05f),
            cornerRadius = CornerRadius(6f, 6f)
        )
    }
}