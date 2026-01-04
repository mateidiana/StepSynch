package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun MiniLakeIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Pond base gradient (larger, less margin) ---
        val waterGradient = Brush.radialGradient(
            colors = listOf(
                Color(0xFF81D4FA),
                Color(0xFF4FC3F7)
            ),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.35f // larger radius
        )

        drawOval(
            brush = waterGradient,
            topLeft = Offset(w * 0.15f, h * 0.4f), // shifted outward
            size = Size(w * 0.7f, h * 0.2f) // larger pond
        )

        // --- Lily pads (larger, slightly repositioned) ---
        drawCircle(
            color = Color(0xFF4CAF50),
            radius = w * 0.045f, // bigger
            center = Offset(w * 0.35f, h * 0.48f)
        )
        drawCircle(
            color = Color(0xFF4CAF50),
            radius = w * 0.04f, // bigger
            center = Offset(w * 0.6f, h * 0.52f)
        )
        drawCircle(
            color = Color(0xFF4CAF50),
            radius = w * 0.03f, // additional lily pad for fullness
            center = Offset(w * 0.5f, h * 0.55f)
        )

        // --- Water highlights (larger) ---
        drawOval(
            color = Color.White.copy(alpha = 0.1f),
            topLeft = Offset(w * 0.25f, h * 0.45f),
            size = Size(w * 0.5f, h * 0.08f)
        )
    }
}
