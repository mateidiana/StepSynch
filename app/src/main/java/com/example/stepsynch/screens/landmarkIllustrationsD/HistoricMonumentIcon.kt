package com.example.stepsynch.screens.landmarkIllustrationsD

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun HistoricMonumentIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // Pedestal
        drawRoundRect(
            color = color.copy(alpha = 0.85f),
            topLeft = Offset(w * 0.3f, h * 0.55f),
            size = Size(w * 0.4f, h * 0.2f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        // Statue body
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.44f, h * 0.28f),
            size = Size(w * 0.12f, h * 0.3f),
            cornerRadius = CornerRadius(10f, 10f)
        )

        // Head
        drawCircle(
            color = color,
            radius = w * 0.06f,
            center = Offset(w * 0.5f, h * 0.25f)
        )
    }
}
