package com.example.stepsynch.screens.landmarkIllustrationsM

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun PineGroveIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        fun drawPineTree(
            centerX: Float,
            trunkHeight: Float,
            canopyScale: Float,
            depthAlpha: Float
        ) {
            // Trunk
            drawRoundRect(
                color = color.copy(alpha = depthAlpha),
                topLeft = Offset(centerX - w * 0.02f, h * 0.55f),
                size = Size(w * 0.04f, trunkHeight),
                cornerRadius = CornerRadius(6f, 6f)
            )

            // Canopy layers
            val canopyColor = color.copy(alpha = depthAlpha)
            val baseY = h * 0.25f

            repeat(3) { layer ->
                val layerWidth = w * (0.25f - layer * 0.05f) * canopyScale
                val layerHeight = h * 0.12f

                val path = Path().apply {
                    moveTo(centerX, baseY + layer * h * 0.08f)
                    lineTo(centerX - layerWidth / 2, baseY + layer * h * 0.08f + layerHeight)
                    lineTo(centerX + layerWidth / 2, baseY + layer * h * 0.08f + layerHeight)
                    close()
                }

                drawPath(
                    path = path,
                    color = canopyColor
                )
            }

            // Subtle highlight
            drawCircle(
                color = Color.White.copy(alpha = 0.12f * depthAlpha),
                radius = w * 0.08f * canopyScale,
                center = Offset(centerX, h * 0.28f)
            )
        }

        // Left tree (slightly back)
        drawPineTree(
            centerX = w * 0.32f,
            trunkHeight = h * 0.18f,
            canopyScale = 0.85f,
            depthAlpha = 0.75f
        )

        // Center tree (foreground)
        drawPineTree(
            centerX = w * 0.5f,
            trunkHeight = h * 0.22f,
            canopyScale = 1f,
            depthAlpha = 1f
        )

        // Right tree (slightly back)
        drawPineTree(
            centerX = w * 0.68f,
            trunkHeight = h * 0.18f,
            canopyScale = 0.85f,
            depthAlpha = 0.75f
        )
    }
}
