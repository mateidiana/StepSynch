package com.example.stepsynch.screens.map
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NordicWalking
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stepsynch.models.Region
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.stepsynch.models.RegionType

@Composable
fun RegionDetailPanel(
    region: Region,
    energy: Int,
    onClose: () -> Unit,
    onStartExploring: (Region) -> Unit
) {
    val deepGreen = Color(0xFF3E5622)
    val darkGreen = Color(0xFF172815)
    val olive = Color(0xFF83781B)
    val lightGreen = Color(0xFF95B46A)
    val surfaceGreen = Color(0xFFF8FAF6)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {

            // ─── Header ─────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = region.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkGreen
                    )

                    Text(
                        text = when (region.type) {
                            RegionType.NATURE -> "Nature Region"
                            RegionType.CITY -> "City Region"
                        },
                        color = deepGreen.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }

                IconButton(onClick = onClose) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = deepGreen
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // ─── Stats cards ─────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    label = "Energy Cost",
                    value = region.energyCost.toString(),
                    icon = Icons.Default.Bolt,
                    iconColor = olive
                )

                StatCard(
                    label = "Steps Required",
                    value = region.stepsRequired.toString(),
                    icon = Icons.Default.NordicWalking,
                    iconColor = deepGreen
                )

                if (region.teamRequired){
                    StatCard(
                        label = "Required",
                        value = "Team",
                        icon = Icons.Default.Star,
                        iconColor = deepGreen
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // ─── Action button ───────────────────────
            Button(
                onClick = { onStartExploring(region)  },
                enabled = energy >= region.energyCost,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (energy >= region.energyCost) deepGreen else deepGreen.copy(alpha = 0.3f),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = if (energy >= region.energyCost)
                        "Start Exploring"
                    else
                        "Not Enough Energy",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

