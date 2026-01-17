package com.example.stepsynch.screens.mapDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun PlayerIndicator(
    x: Float,
    y: Float,
    width: Int,
    height: Int
) {
    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    (x * width).toInt(),
                    (y * height).toInt()
                )
            }
    ) {
        Box(
            Modifier
                .size(12.dp)
                .background(Color(0xFF3E5622), CircleShape)
                .border(3.dp, Color.White, CircleShape)
        )
    }
}
