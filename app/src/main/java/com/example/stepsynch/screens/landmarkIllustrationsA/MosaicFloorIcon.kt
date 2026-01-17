package com.example.stepsynch.screens.landmarkIllustrationsA

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun MosaicFloorIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // --- Base stone floor ---
        drawRect(
            color = Color(0xFFB1AA9E),
            topLeft = Offset(w * 0.2f, h * 0.35f),
            size = Size(w * 0.6f, h * 0.35f)
        )

        // --- Mosaic tiles (pattern) ---
        val tileSize = w * 0.12f
        val startX = w * 0.26f
        val startY = h * 0.4f

        repeat(3) { row ->
            repeat(3) { col ->
                drawRect(
                    color = if ((row + col) % 2 == 0)
                        Color(0xFF8E887D)
                    else
                        Color(0xFF9C9589),
                    topLeft = Offset(
                        startX + col * tileSize,
                        startY + row * tileSize
                    ),
                    size = Size(tileSize * 0.9f, tileSize * 0.9f)
                )
            }
        }

        // --- Missing tile gaps ---
        drawRect(
            color = Color(0xFF6E685E),
            topLeft = Offset(startX + tileSize, startY + tileSize),
            size = Size(tileSize * 0.6f, tileSize * 0.6f)
        )

        // --- Cracks ---
        drawLine(
            color = Color.Black.copy(alpha = 0.25f),
            start = Offset(w * 0.32f, h * 0.46f),
            end = Offset(w * 0.58f, h * 0.62f),
            strokeWidth = 2f
        )

        // --- Moss creeping in ---
        drawOval(
            color = color.copy(alpha = 0.35f),
            topLeft = Offset(w * 0.22f, h * 0.6f),
            size = Size(w * 0.18f, h * 0.08f)
        )

        // --- Worn highlight ---
        drawRect(
            color = Color.White.copy(alpha = 0.06f),
            topLeft = Offset(w * 0.2f, h * 0.35f),
            size = Size(w * 0.6f, h * 0.12f)
        )
    }
}