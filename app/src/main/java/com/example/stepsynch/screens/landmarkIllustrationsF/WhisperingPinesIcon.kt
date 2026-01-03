package com.example.stepsynch.screens.landmarkIllustrationsF

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius

@Composable
fun WhisperingPinesIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Back pine silhouettes ---
        drawOval(
            color = Color(0xFF254117),
            topLeft = Offset(w * 0.18f, h * 0.15f),
            size = Size(w * 0.22f, h * 0.6f)
        )
        drawOval(
            color = Color(0xFF2E5D2E),
            topLeft = Offset(w * 0.6f, h * 0.18f),
            size = Size(w * 0.22f, h * 0.58f)
        )

        // --- Front pine gradient ---
        val pineGradient = Brush.verticalGradient(
            colors = listOf(
                color.copy(alpha = 0.95f),
                Color(0xFF3E7A2E),
                Color(0xFF254117)
            )
        )

        drawOval(
            brush = pineGradient,
            topLeft = Offset(w * 0.38f, h * 0.05f),
            size = Size(w * 0.26f, h * 0.7f)
        )

        // --- Trunks ---
        drawRect(
            color = Color(0xFF5A3A1A),
            topLeft = Offset(w * 0.46f, h * 0.6f),
            size = Size(w * 0.08f, h * 0.25f)
        )
        drawRect(
            color = Color(0xFF5A3A1A).copy(alpha = 0.85f),
            topLeft = Offset(w * 0.26f, h * 0.62f),
            size = Size(w * 0.06f, h * 0.22f)
        )

        // --- Soft canopy highlight ---
        drawOval(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.42f, h * 0.12f),
            size = Size(w * 0.18f, h * 0.22f)
        )
    }
}