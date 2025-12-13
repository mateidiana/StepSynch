package com.example.stepsynch.screens.map
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepsynch.models.Region
import com.example.stepsynch.models.RegionStatus

@Composable
fun MapCanvas(
    regions: List<Region>,
    onRegionClick: (Region) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .offset(x = (-30).dp, y = (-30).dp)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val xShift = -0.1f
        val yShift = -0.08f
        // --- Path lines ---
        Canvas(modifier = Modifier.fillMaxSize()) {
            for (i in 0 until regions.size - 1) {
                val start = regions[i]
                val end = regions[i + 1]

                drawLine(
                    color = if (start.status == RegionStatus.COMPLETED)
                        Color(0xFF709255)
                    else
                        Color(0xFF3E5622).copy(alpha = 0.3f),
                    start = Offset(
                        size.width * start.x,
                        size.height * start.y
                    ),
                    end = Offset(
                        size.width * end.x,
                        size.height * end.y
                    ),
                    strokeWidth = 6f
                )
            }
        }

        // --- Region nodes + labels ---
        regions.forEach { region ->
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            ((region.x + xShift) * width * 1.1f).toInt(),
                            ((region.y + yShift) * height * 1.1f).toInt()
                        )
                    }
                    .wrapContentSize() // allow stacking label + node
                    .clickable(
                        enabled = region.status != RegionStatus.LOCKED
                    ) { onRegionClick(region) },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // --- Label above node ---
                    Text(
                        text = region.name,
                        fontSize = 12.sp,
                        color = Color(0xFF172815),
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.95f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp)) // keep spacing same as before

                    // --- Region node (unchanged) ---
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                when (region.status) {
                                    RegionStatus.COMPLETED -> Color(0xFF709255)
                                    RegionStatus.EXPLORING -> Color(0xFF83781B)
                                    RegionStatus.UNLOCKED -> Color(0xFF95B46A)
                                    RegionStatus.LOCKED -> Color(0xFF3E5622).copy(alpha = 0.3f)
                                },
                                CircleShape
                            )
                            .border(
                                4.dp,
                                Color.White,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = when (region.status) {
                                RegionStatus.COMPLETED -> Icons.Default.Check
                                RegionStatus.LOCKED -> Icons.Default.Lock
                                else -> Icons.Default.LocationOn
                            },
                            contentDescription = region.name,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

