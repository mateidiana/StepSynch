package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun ForestStreamIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Stream shadow (banks) ---
        val shadowPath = Path().apply {
            moveTo(w * 0.3f, 0f)
            cubicTo(w * 0.2f, h * 0.3f, w * 0.4f, h * 0.6f, w * 0.3f, h)
            lineTo(w * 0.55f, h)
            cubicTo(w * 0.65f, h * 0.6f, w * 0.45f, h * 0.3f, w * 0.6f, 0f)
            close()
        }

        drawPath(
            path = shadowPath,
            color = Color(0xFF2E3D2F).copy(alpha = 0.45f)
        )

        // --- Water gradient ---
        val waterGradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 0.85f),
                color.copy(alpha = 0.6f),
                color.copy(alpha = 0.45f)
            )
        )

        // --- Main stream ---
        val streamPath = Path().apply {
            moveTo(w * 0.34f, 0f)
            cubicTo(w * 0.28f, h * 0.3f, w * 0.45f, h * 0.6f, w * 0.36f, h)
            lineTo(w * 0.5f, h)
            cubicTo(w * 0.6f, h * 0.6f, w * 0.42f, h * 0.3f, w * 0.56f, 0f)
            close()
        }

        drawPath(
            path = streamPath,
            brush = waterGradient
        )

        // --- Surface highlight ---
        drawPath(
            path = streamPath,
            color = Color.White.copy(alpha = 0.08f),
            style = Stroke(width = w * 0.02f)
        )
    }
}