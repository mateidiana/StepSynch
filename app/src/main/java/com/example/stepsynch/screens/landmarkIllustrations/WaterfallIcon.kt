package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun WaterfallIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        /* ---------------- Top rock ledge ---------------- */
        drawRoundRect(
            color = Color(0xFF3E5622).copy(alpha = 0.85f),
            topLeft = Offset(w * 0.28f, h * 0.06f),
            size = Size(w * 0.44f, h * 0.08f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        /* ---------------- Main water column ---------------- */
        val waterfallLeft = w * 0.32f
        val waterfallWidth = w * 0.36f
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(waterfallLeft, h * 0.14f),
            size = Size(waterfallWidth, h * 0.62f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        /* ---------------- Vertical highlights (multiple white lines) ---------------- */
        val highlightCount = 3
        val spacing = waterfallWidth / (highlightCount + 1)
        val highlightWidth = w * 0.006f

        // Choose which lines are fully white
        val highlightColors = listOf(
            Color.White,        // left line fully white
            Color.White.copy(alpha = 0.8f), // middle line slightly transparent white
            Color.White         // right line fully white
        )

        for (i in 1..highlightCount) {
            val x = waterfallLeft + spacing * i - (highlightWidth / 2)
            drawRoundRect(
                color = highlightColors[i - 1],
                topLeft = Offset(x, h * 0.18f),
                size = Size(highlightWidth, h * 0.56f),
                cornerRadius = CornerRadius(6f, 6f)
            )
        }

        /* ---------------- Horizontal water streaks ---------------- */
        val streakColor = Color.White.copy(alpha = 0.18f)
        val streakWidth = w * 0.1f
        for (i in 0..4) {
            val y = h * (0.28f + i * 0.1f)
            drawLine(
                color = streakColor,
                start = Offset(waterfallLeft + w * 0.02f, y),
                end = Offset(waterfallLeft + w * 0.02f + streakWidth, y),
                strokeWidth = 2f
            )
        }

        /* ---------------- Side streams ---------------- */
        drawRoundRect(
            color = color.copy(alpha = 0.65f),
            topLeft = Offset(w * 0.28f, h * 0.22f),
            size = Size(w * 0.05f, h * 0.52f),
            cornerRadius = CornerRadius(12f, 12f)
        )
        drawRoundRect(
            color = color.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.68f, h * 0.26f),
            size = Size(w * 0.045f, h * 0.48f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        /* ---------------- Blue foam / mist ---------------- */
        val foamColor = color.copy(alpha = 0.35f)
        drawCircle(
            color = foamColor,
            radius = w * 0.18f,
            center = Offset(w * 0.5f, h * 0.82f)
        )
        drawCircle(
            color = foamColor.copy(alpha = 0.2f),
            radius = w * 0.24f,
            center = Offset(w * 0.5f, h * 0.86f)
        )

        /* ---------------- Side spray ---------------- */
        drawCircle(
            color = foamColor.copy(alpha = 0.15f),
            radius = w * 0.06f,
            center = Offset(w * 0.38f, h * 0.78f)
        )
        drawCircle(
            color = foamColor.copy(alpha = 0.12f),
            radius = w * 0.05f,
            center = Offset(w * 0.62f, h * 0.8f)
        )
    }
}


