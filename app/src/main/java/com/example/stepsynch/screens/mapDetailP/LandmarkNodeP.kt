package com.example.stepsynch.screens.mapDetailP

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.stepsynch.models.Landmark
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.stepsynch.screens.mapDetailP.LandmarkIllustrationP

@Composable
fun LandmarkNodeP(
    landmark: Landmark,
    width: Int,
    height: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .offset {
                IntOffset(
                    (landmark.x * width).toInt(),
                    (landmark.y * height).toInt()
                )
            }
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    if (landmark.collected) Color(0xFF709255) else Color.White,
                    CircleShape
                )
                .border(2.dp, Color(0xFF83781B), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (landmark.collected) {
                LandmarkIllustrationP(
                    name = landmark.name,
                    collected = landmark.collected,
                    modifier = Modifier.requiredSize(100.dp)
                        .offset(
                            x = when (landmark.name) {
                                "High Camp" -> (50).dp
                                else -> (-60).dp
                            },
                            y = (-45).dp
                        )
                )
            }
            Icon(
                imageVector = if (landmark.collected) Icons.Default.Check else Icons.Default.LocationOn,
                contentDescription = null,
                tint = if (landmark.collected) Color.White else Color(0xFF83781B)
            )
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = landmark.name,
            fontSize = 11.sp,
            color = Color(0xFF172815),
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(6.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}