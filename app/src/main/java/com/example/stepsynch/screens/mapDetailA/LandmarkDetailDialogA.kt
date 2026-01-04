package com.example.stepsynch.screens.mapDetailA

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stepsynch.models.Landmark
import androidx.compose.ui.graphics.Color
import com.example.stepsynch.screens.mapDetailA.LandmarkIllustrationA

@Composable
fun LandmarkDetailDialogA(
    landmark: Landmark?,
    onDismiss: () -> Unit,
    onCollect: () -> Unit
) {
    if (landmark == null) return

    val palette = listOf(
        Color(0xFF709255), // soft green
        Color(0xFF83781B), // brown
        Color(0xFF95B46A), // light green
        Color(0xFF3E5622)  // dark green
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.White,

        /* ---------- Title ---------- */
        title = {
            Text(
                text = landmark.name,
                fontWeight = FontWeight.Bold,
                color = palette[3]
            )
        },

        /* ---------- Content ---------- */
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LandmarkIllustrationA(
                    name = landmark.name,
                    collected = landmark.collected,
                    modifier = Modifier.size(140.dp)
                )

                Text(
                    text = if (landmark.collected)
                        "You have already collected this landmark."
                    else
                        "Explore this landmark to earn rewards.",
                    color = palette[0]
                )
            }
        },

        /* ---------- Confirm ---------- */
        confirmButton = {
            if (!landmark.collected) {
                Button(
                    onClick = onCollect,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = palette[1],
                        contentColor = Color.White
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Collect for 125")
                        Icon(
                            imageVector = Icons.Default.Bolt,
                            contentDescription = "Energy"
                        )
                    }
                }
            }
        },

        /* ---------- Dismiss ---------- */
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = palette[2]
                )
            ) {
                Text("Close")
            }
        }
    )
}