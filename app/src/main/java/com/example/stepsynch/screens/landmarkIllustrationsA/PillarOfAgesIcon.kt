package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun PillarOfAgesIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Stone shaft gradient ---
        val pillarGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB5ADA0),
                Color(0xFF8E877B),
                Color(0xFF6F695F)
            )
        )

        // --- Base ---
        drawRoundRect(
            color = Color(0xFF7A7468),
            topLeft = Offset(w * 0.32f, h * 0.75f),
            size = Size(w * 0.36f, h * 0.1f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // --- Main pillar body ---
        drawRoundRect(
            brush = pillarGradient,
            topLeft = Offset(w * 0.4f, h * 0.18f),
            size = Size(w * 0.2f, h * 0.6f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // --- Capital (top) ---
        drawRoundRect(
            color = Color(0xFF9C9487),
            topLeft = Offset(w * 0.36f, h * 0.12f),
            size = Size(w * 0.28f, h * 0.08f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // --- Erosion chips ---
        drawRect(
            color = Color(0xFF5E594F),
            topLeft = Offset(w * 0.48f, h * 0.32f),
            size = Size(w * 0.04f, h * 0.06f)
        )

        drawRect(
            color = Color(0xFF5E594F),
            topLeft = Offset(w * 0.42f, h * 0.48f),
            size = Size(w * 0.03f, h * 0.05f)
        )

        // --- Moss patches ---
        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.42f, h * 0.38f),
            size = Size(w * 0.08f, h * 0.06f)
        )

        drawOval(
            color = color.copy(alpha = 0.3f),
            topLeft = Offset(w * 0.48f, h * 0.6f),
            size = Size(w * 0.06f, h * 0.05f)
        )

        // --- Subtle edge highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.42f, h * 0.18f),
            size = Size(w * 0.06f, h * 0.6f),
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
}