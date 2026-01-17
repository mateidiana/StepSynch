package com.example.stepsynch.screens.landmarkIllustrationsP

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun KnifeEdgePassIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Shadow at base ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.3f, h * 0.78f),
            size = androidx.compose.ui.geometry.Size(w * 0.4f, h * 0.12f)
        )

        // --- Ridge path (knife-edge) ---
        val ridgePath = Path().apply {
            moveTo(w * 0.2f, h * 0.6f)
            lineTo(w * 0.5f, h * 0.2f)
            lineTo(w * 0.8f, h * 0.6f)
            close()
        }
        drawPath(
            path = ridgePath,
            color = Color(0xFF7A7A7A)
        )

        // --- Edge highlight ---
        drawLine(
            color = Color.White.copy(alpha = 0.15f),
            start = Offset(w * 0.5f, h * 0.2f),
            end = Offset(w * 0.5f, h * 0.6f),
            strokeWidth = 2f
        )

        // --- Drop shadows ---
        drawPath(
            path = Path().apply {
                moveTo(w * 0.5f, h * 0.2f)
                lineTo(w * 0.45f, h * 0.6f)
                lineTo(w * 0.55f, h * 0.6f)
                close()
            },
            color = Color.Black.copy(alpha = 0.2f)
        )
    }
}