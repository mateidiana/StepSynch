package com.example.stepsynch.screens.landmarkIllustrationsW

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun FlowerGardenIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Grass patch (larger) ---
        drawOval(
            color = Color(0xFF95B46A),
            topLeft = Offset(w * 0.1f, h * 0.55f),
            size = androidx.compose.ui.geometry.Size(w * 0.8f, h * 0.25f)
        )

        // --- Flower clusters (larger, spread out) ---
        val flowerCenters = listOf(
            Offset(w * 0.3f, h * 0.6f),
            Offset(w * 0.45f, h * 0.65f),
            Offset(w * 0.6f, h * 0.58f),
            Offset(w * 0.7f, h * 0.63f)
        )

        flowerCenters.forEach { center ->
            drawCircle(
                color = Color(0xFFFF6F61),
                radius = w * 0.04f, // slightly bigger
                center = center
            )
            drawCircle(
                color = Color(0xFFFFD700),
                radius = w * 0.02f, // slightly bigger
                center = center
            )
        }

        // --- Subtle highlight on grass (larger) ---
        drawOval(
            color = Color.White.copy(alpha = 0.08f),
            topLeft = Offset(w * 0.15f, h * 0.58f),
            size = androidx.compose.ui.geometry.Size(w * 0.7f, h * 0.07f)
        )
    }
}
