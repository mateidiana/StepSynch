package com.example.stepsynch.screens.mapDetailA

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.map.StatCard

@Composable
fun MapDetailBottomStatsA(
    collected: Int,
    total: Int
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 32.dp
            )
            //.padding(16.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            StatCard("Landmarks", "$collected/$total", Icons.Default.Star, Color(0xFF83781B))
            StatCard("Complete to gain", "+250", Icons.Default.Bolt, Color(0xFF83781B))
            //StatCard("Required", "Team member", Icons.Default.EmojiEvents, Color(0xFF83781B))
        }
    }
}