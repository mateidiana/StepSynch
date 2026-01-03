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
fun RooftopGardenIcon(modifier: Modifier, color: Color) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Building base
        drawRoundRect(
            color = color.copy(alpha = 0.75f),
            topLeft = Offset(w * 0.22f, h * 0.48f),
            size = Size(w * 0.56f, h * 0.32f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // Roof edge
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.22f, h * 0.45f),
            size = Size(w * 0.56f, h * 0.07f),
            cornerRadius = CornerRadius(18f, 18f)
        )

        // Garden base
        drawCircle(
            color = color.copy(alpha = 0.65f),
            radius = w * 0.2f,
            center = Offset(w * 0.5f, h * 0.38f)
        )

        // Foliage clusters
        listOf(
            Offset(w * 0.45f, h * 0.36f),
            Offset(w * 0.55f, h * 0.38f),
            Offset(w * 0.5f, h * 0.32f)
        ).forEach {
            drawCircle(
                color = Color.White.copy(alpha = 0.25f),
                radius = w * 0.07f,
                center = it
            )
        }
    }
}

