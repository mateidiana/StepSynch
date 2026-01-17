package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun SculpturePathIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Path base (larger) ---
        drawRect(
            color = Color(0xFFBFAF85),
            topLeft = Offset(w * 0.2f, h * 0.55f),
            size = androidx.compose.ui.geometry.Size(w * 0.6f, h * 0.1f)
        )

        // --- Sculptures (larger, slightly repositioned) ---
        val sculptureCenters = listOf(
            Offset(w * 0.28f, h * 0.5f),
            Offset(w * 0.5f, h * 0.48f),
            Offset(w * 0.72f, h * 0.5f)
        )

        sculptureCenters.forEach { center ->
            drawCircle(
                color = Color(0xFF7A7A7A),
                radius = w * 0.05f, // larger
                center = center
            )
        }

        // --- Shadow on path (larger) ---
        drawRect(
            color = Color.Black.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.2f, h * 0.57f),
            size = androidx.compose.ui.geometry.Size(w * 0.6f, h * 0.04f)
        )
    }
}
