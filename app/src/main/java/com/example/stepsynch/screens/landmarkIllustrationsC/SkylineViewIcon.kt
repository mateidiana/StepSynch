package com.example.stepsynch.screens.landmarkIllustrationsC

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun SkylineViewIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Background skyline
        listOf(
            Offset(w * 0.18f, h * 0.45f) to Size(w * 0.14f, h * 0.3f),
            Offset(w * 0.38f, h * 0.4f) to Size(w * 0.16f, h * 0.35f),
            Offset(w * 0.6f, h * 0.48f) to Size(w * 0.14f, h * 0.27f)
        ).forEach {
            drawRoundRect(
                color = color.copy(alpha = 0.6f),
                topLeft = it.first,
                size = it.second,
                cornerRadius = CornerRadius(14f, 14f)
            )
        }

        // Foreground towers
        listOf(
            Offset(w * 0.28f, h * 0.35f) to Size(w * 0.16f, h * 0.45f),
            Offset(w * 0.48f, h * 0.28f) to Size(w * 0.18f, h * 0.52f)
        ).forEach {
            drawRoundRect(
                color = color,
                topLeft = it.first,
                size = it.second,
                cornerRadius = CornerRadius(18f, 18f)
            )
        }

        // Window highlight
        drawRoundRect(
            color = Color.White.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.53f, h * 0.3f),
            size = Size(w * 0.04f, h * 0.45f),
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
}

