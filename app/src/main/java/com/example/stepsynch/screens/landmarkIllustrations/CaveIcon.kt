package com.example.stepsynch.screens.landmarkIllustrations

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun CaveIcon(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        val archBrush = Brush.radialGradient(
            colors = listOf(
                Color(0xFF2B1A0D),
                Color(0xFF5A3A1A),
                Color(0xFFB08B4F)
            ),
            center = Offset(w * 0.5f, h * 0.6f),
            radius = w * 0.35f
        )

        val waterBrush = Brush.radialGradient(
            colors = listOf(
                Color(0xFF1A3B4B),
                Color(0xFF2F5D6B)
            ),
            center = Offset(w * 0.5f, h * 0.7f),
            radius = w * 0.3f,
            tileMode = androidx.compose.ui.graphics.TileMode.Clamp
        )

        // Align edges for water and arch
        val leftEdge = w * 0.15f
        val rightEdge = w * 0.85f
        val caveWidth = rightEdge - leftEdge
        val extraWidth = caveWidth * 1.2f - caveWidth
        val archLeft = leftEdge - extraWidth / 2  // shift left to center

        // Base cave entrance (blue water/dark opening)
        drawOval(
            color = Color(0xFF2F5D6B),
            topLeft = Offset(leftEdge, h * 0.5f),
            size = Size(caveWidth, h * 0.4f)
        )

        // Cave arch/shadow (brown, taller/elongated)
        drawArc(
            brush = archBrush,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(archLeft, h * 0.42f),
            size = Size(caveWidth * 1.2f, h * 0.6f)
        )

        // Ground rocks in front
        drawCircle(
            color = Color(0xFF709255),
            radius = w * 0.05f,
            center = Offset(w * 0.3f, h * 0.85f)
        )
        drawCircle(
            color = Color(0xFF3E5622),
            radius = w * 0.04f,
            center = Offset(w * 0.7f, h * 0.85f)
        )

        // Optional small vines
        drawLine(
            color = Color(0xFF95B46A),
            start = Offset(w * 0.25f, h * 0.55f),
            end = Offset(w * 0.25f, h * 0.7f),
            strokeWidth = 2f
        )
        drawLine(
            color = Color(0xFF95B46A),
            start = Offset(w * 0.75f, h * 0.55f),
            end = Offset(w * 0.75f, h * 0.7f),
            strokeWidth = 2f
        )
    }
}