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
fun CrumblingGateIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Stone gradient ---
        val stoneGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFFB8B0A2),
                Color(0xFF8D8679)
            )
        )

        // --- Left pillar ---
        drawRoundRect(
            brush = stoneGradient,
            topLeft = Offset(w * 0.18f, h * 0.18f),
            size = Size(w * 0.18f, h * 0.62f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // --- Right pillar ---
        drawRoundRect(
            brush = stoneGradient,
            topLeft = Offset(w * 0.64f, h * 0.28f),
            size = Size(w * 0.18f, h * 0.52f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // --- Broken lintel ---
        drawRoundRect(
            color = Color(0xFF9C9487),
            topLeft = Offset(w * 0.22f, h * 0.12f),
            size = Size(w * 0.46f, h * 0.1f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // --- Missing chunk (break) ---
        drawRect(
            color = Color(0xFF6E685E),
            topLeft = Offset(w * 0.48f, h * 0.12f),
            size = Size(w * 0.1f, h * 0.06f)
        )

        // --- Inner shadow (archway depth) ---
        drawRect(
            color = Color.Black.copy(alpha = 0.2f),
            topLeft = Offset(w * 0.36f, h * 0.32f),
            size = Size(w * 0.28f, h * 0.48f)
        )

        // --- Fallen stones ---
        drawOval(
            color = Color(0xFF7A7468),
            topLeft = Offset(w * 0.25f, h * 0.78f),
            size = Size(w * 0.12f, h * 0.08f)
        )
        drawOval(
            color = Color(0xFF6F695F),
            topLeft = Offset(w * 0.6f, h * 0.8f),
            size = Size(w * 0.14f, h * 0.1f)
        )

        // --- Moss accents ---
        drawCircle(
            color = color.copy(alpha = 0.35f),
            radius = w * 0.04f,
            center = Offset(w * 0.22f, h * 0.35f)
        )
        drawCircle(
            color = color.copy(alpha = 0.3f),
            radius = w * 0.035f,
            center = Offset(w * 0.7f, h * 0.5f)
        )

        // --- Edge highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.2f, h * 0.18f),
            size = Size(w * 0.14f, h * 0.06f),
            cornerRadius = CornerRadius(6f, 6f)
        )
    }
}