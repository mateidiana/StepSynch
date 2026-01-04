package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun WelcomeFountainIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Basin shadow (larger) ---
        drawOval(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.2f, h * 0.63f),
            size = Size(w * 0.6f, h * 0.12f)
        )

        // --- Fountain basin (larger) ---
        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF95B46A), Color(0xFF709255))
            ),
            topLeft = Offset(w * 0.2f, h * 0.58f),
            size = Size(w * 0.6f, h * 0.14f)
        )

        // --- Water jets (larger and more prominent) ---
        drawOval(
            color = Color.White.copy(alpha = 0.7f),
            topLeft = Offset(w * 0.42f, h * 0.36f),
            size = Size(w * 0.16f, h * 0.14f)
        )
        drawOval(
            color = Color.White.copy(alpha = 0.6f),
            topLeft = Offset(w * 0.35f, h * 0.38f),
            size = Size(w * 0.08f, h * 0.08f)
        )
        drawOval(
            color = Color.White.copy(alpha = 0.5f),
            topLeft = Offset(w * 0.57f, h * 0.38f),
            size = Size(w * 0.08f, h * 0.08f)
        )

        // --- Water sparkle highlight (larger) ---
        drawCircle(
            color = Color.White.copy(alpha = 0.2f),
            radius = w * 0.06f,
            center = Offset(w * 0.5f, h * 0.43f)
        )
    }
}
