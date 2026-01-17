package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun TheaterIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Stage frame
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.18f, h * 0.25f),
            size = Size(w * 0.64f, h * 0.5f),
            cornerRadius = CornerRadius(14f, 14f)
        )

        // Curtain opening
        drawOval(
            color = Color.Black.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.32f, h * 0.38f),
            size = Size(w * 0.36f, h * 0.28f)
        )

        // Top accent
        drawRoundRect(
            color = Color.White.copy(alpha = 0.15f),
            topLeft = Offset(w * 0.32f, h * 0.28f),
            size = Size(w * 0.36f, h * 0.08f),
            cornerRadius = CornerRadius(8f, 8f)
        )
    }
}
