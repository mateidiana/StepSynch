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
fun BoulderFieldIcon(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height

        data class Boulder(
            val center: Offset,
            val radius: Float,
            val alpha: Float
        )

        val boulders = listOf(
            Boulder(Offset(w * 0.35f, h * 0.62f), w * 0.13f, 0.7f),
            Boulder(Offset(w * 0.5f, h * 0.56f), w * 0.15f, 1f),   // foreground
            Boulder(Offset(w * 0.65f, h * 0.64f), w * 0.11f, 0.75f),
            Boulder(Offset(w * 0.58f, h * 0.7f), w * 0.09f, 0.6f)
        )

        boulders.forEach { boulder ->
            // Main rock
            drawCircle(
                color = color.copy(alpha = boulder.alpha),
                radius = boulder.radius,
                center = boulder.center
            )

            // Highlight
            drawCircle(
                color = Color.White.copy(alpha = 0.12f * boulder.alpha),
                radius = boulder.radius * 0.4f,
                center = Offset(
                    boulder.center.x - boulder.radius * 0.2f,
                    boulder.center.y - boulder.radius * 0.25f
                )
            )
        }
    }
}

