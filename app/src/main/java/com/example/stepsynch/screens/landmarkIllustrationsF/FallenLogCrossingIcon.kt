package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius

@Composable
fun FallenLogCrossingIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Ground shadow ---
        drawRoundRect(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.12f, h * 0.58f),
            size = Size(w * 0.76f, h * 0.12f),
            cornerRadius = CornerRadius(30f, 30f)
        )

        // --- Log gradient (bark) ---
        val logGradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF3E2A14),
                Color(0xFF5A3A1A),
                Color(0xFF7A4F28)
            ),
            start = Offset(0f, h * 0.55f),
            end = Offset(0f, h * 0.7f)
        )

        // --- Main log ---
        drawRoundRect(
            brush = logGradient,
            topLeft = Offset(w * 0.1f, h * 0.48f),
            size = Size(w * 0.8f, h * 0.14f),
            cornerRadius = CornerRadius(40f, 40f)
        )

        // --- Moss strip on log ---
        drawRoundRect(
            color = Color(0xFF709255).copy(alpha = 0.75f),
            topLeft = Offset(w * 0.16f, h * 0.46f),
            size = Size(w * 0.68f, h * 0.06f),
            cornerRadius = CornerRadius(30f, 30f)
        )

        // --- Moss clumps ---
        drawCircle(
            color = Color(0xFF95B46A).copy(alpha = 0.7f),
            radius = w * 0.06f,
            center = Offset(w * 0.32f, h * 0.47f)
        )
        drawCircle(
            color = Color(0xFF95B46A).copy(alpha = 0.6f),
            radius = w * 0.05f,
            center = Offset(w * 0.55f, h * 0.46f)
        )

        // --- Log highlight ---
        drawRoundRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.2f, h * 0.5f),
            size = Size(w * 0.6f, h * 0.04f),
            cornerRadius = CornerRadius(20f, 20f)
        )
    }
}