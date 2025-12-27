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
fun ArtGalleryIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Building body
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.2f, h * 0.28f),
            size = Size(w * 0.6f, h * 0.48f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // Frame
        drawRoundRect(
            color = Color.White.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.32f, h * 0.4f),
            size = Size(w * 0.36f, h * 0.22f),
            cornerRadius = CornerRadius(6f, 6f)
        )

        // Artwork dot
        drawCircle(
            color = Color.White.copy(alpha = 0.6f),
            radius = w * 0.05f,
            center = Offset(w * 0.5f, h * 0.51f)
        )
    }
}
