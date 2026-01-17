package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun CentralParkIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Grass base
        drawOval(
            color = color.copy(alpha = 0.85f),
            topLeft = Offset(w * 0.15f, h * 0.5f),
            size = Size(w * 0.7f, h * 0.28f)
        )

        // Trees
        drawCircle(
            color = Color(0xFF3E5622),
            radius = w * 0.14f,
            center = Offset(w * 0.38f, h * 0.42f)
        )

        drawCircle(
            color = Color(0xFF3E5622),
            radius = w * 0.16f,
            center = Offset(w * 0.58f, h * 0.4f)
        )

        // Highlight
        drawCircle(
            color = Color.White.copy(alpha = 0.12f),
            radius = w * 0.1f,
            center = Offset(w * 0.5f, h * 0.36f)
        )
    }
}
