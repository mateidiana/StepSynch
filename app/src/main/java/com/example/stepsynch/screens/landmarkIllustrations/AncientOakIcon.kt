package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AncientOakIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        // --- Trunk gradient ---
        val trunkGradient = Brush.verticalGradient(
            colors = listOf(
                Color(0xFF5A3A1A),
                Color(0xFF3E5622)
            )
        )

        // --- Canopy gradient (green) ---
        val canopyGradient = Brush.radialGradient(
            colors = listOf(
                Color(0xFF4A5D23),
                Color(0xFF3E7A2E),
                Color(0xFF254117)
            ),
            center = Offset(w / 2, h * 0.35f),
            radius = w * 0.55f
        )

        // --- Trunk ---
        drawRoundRect(
            brush = trunkGradient,
            topLeft = Offset(w * 0.44f, h * 0.45f),
            size = Size(w * 0.12f, h * 0.4f),
            cornerRadius = CornerRadius(12f, 12f)
        )

        // --- Roots ---
        drawRoundRect(
            color = Color(0xFF3E5622),
            topLeft = Offset(w * 0.38f, h * 0.75f),
            size = Size(w * 0.24f, h * 0.08f),
            cornerRadius = CornerRadius(20f, 20f)
        )

        // --- Canopy (layered blobs for depth) ---
        drawCircle(
            brush = canopyGradient,
            radius = w * 0.32f,
            center = Offset(w * 0.5f, h * 0.3f)
        )
        drawCircle(
            color = Color(0xFF4B7B2D),
            radius = w * 0.24f,
            center = Offset(w * 0.32f, h * 0.38f)
        )
        drawCircle(
            color = Color(0xFF4B7B2D),
            radius = w * 0.22f,
            center = Offset(w * 0.68f, h * 0.38f)
        )

        // --- Highlight ---
        drawCircle(
            color = Color.White.copy(alpha = 0.12f),
            radius = w * 0.18f,
            center = Offset(w * 0.45f, h * 0.22f)
        )
    }
}

