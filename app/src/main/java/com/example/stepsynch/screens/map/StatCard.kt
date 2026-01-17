package com.example.stepsynch.screens.map
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatCard(
    label: String,
    value: String,
    icon: ImageVector,
    iconColor: Color
) {
    Column(
        modifier = Modifier
            .background(
                Color(0xFFF8FAF6),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = label,
            color = Color(0x993E5622),
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = Color(0xFF172815),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
