package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun LookoutIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        /* ---------- Shadow (depth) ---------- */
        drawRoundRect(
            color = Color.Black.copy(alpha = 0.2f),
            topLeft = Offset(w * 0.44f, h * 0.28f),
            size = Size(w * 0.12f, h * 0.55f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        /* ---------- Support posts ---------- */
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.42f, h * 0.25f),
            size = Size(w * 0.06f, h * 0.55f),
            cornerRadius = CornerRadius(6f, 6f)
        )

        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.52f, h * 0.25f),
            size = Size(w * 0.06f, h * 0.55f),
            cornerRadius = CornerRadius(6f, 6f)
        )

        /* ---------- Platform base ---------- */
        drawRoundRect(
            color = color.copy(alpha = 0.9f),
            topLeft = Offset(w * 0.3f, h * 0.22f),
            size = Size(w * 0.4f, h * 0.09f),
            cornerRadius = CornerRadius(8f, 8f)
        )

        /* ---------- Platform railing ---------- */
        drawRoundRect(
            color = color.copy(alpha = 0.7f),
            topLeft = Offset(w * 0.32f, h * 0.19f),
            size = Size(w * 0.36f, h * 0.04f),
            cornerRadius = CornerRadius(6f, 6f)
        )

        /* ---------- Light highlight / focal point ---------- */
        drawCircle(
            color = Color.White.copy(alpha = 0.18f),
            radius = w * 0.08f,
            center = Offset(w * 0.5f, h * 0.2f)
        )
    }
}

