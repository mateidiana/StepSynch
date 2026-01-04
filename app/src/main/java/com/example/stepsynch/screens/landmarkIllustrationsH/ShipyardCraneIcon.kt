package com.example.stepsynch.screens.landmarkIllustrationsH

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun ShipyardCraneIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Base / dock shadow ---
        drawRect(
            color = Color.Black.copy(alpha = 0.25f),
            topLeft = Offset(w * 0.3f, h * 0.75f),
            size = Size(w * 0.4f, h * 0.1f)
        )

        // --- Vertical mast ---
        drawRect(
            color = Color(0xFF5A3A1A),
            topLeft = Offset(w * 0.48f, h * 0.25f),
            size = Size(w * 0.04f, h * 0.5f)
        )

        // --- Crane arm ---
        drawRect(
            color = Color(0xFF7A4F28),
            topLeft = Offset(w * 0.5f, h * 0.25f),
            size = Size(w * 0.25f, h * 0.04f)
        )

        // --- Counterweight block ---
        drawRect(
            color = Color(0xFF4A2F16),
            topLeft = Offset(w * 0.45f, h * 0.25f),
            size = Size(w * 0.05f, h * 0.05f)
        )

        // --- Hook / line ---
        drawRect(
            color = Color(0xFF3E2A14),
            topLeft = Offset(w * 0.73f, h * 0.29f),
            size = Size(w * 0.01f, h * 0.1f)
        )

        // --- Highlight on arm ---
        drawRect(
            color = Color.White.copy(alpha = 0.12f),
            topLeft = Offset(w * 0.52f, h * 0.26f),
            size = Size(w * 0.2f, h * 0.03f)
        )
    }
}