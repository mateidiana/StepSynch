package com.example.stepsynch.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun WelcomeScreen(navController: NavController) {

    // 🌿 Natural, calming palette
    val lightestGreen = Color(0xFFEAF4E0)
    val forestGreen = Color(0xFF709255)
    val deepGreen = Color(0xFF3E5622)
    val darkGreen = Color(0xFF172815)
    val paleWhiteGreen = Color(0xFFF6FAF2)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered) deepGreen.copy(alpha = 0.8f) else deepGreen,
        label = "hoverColorAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightestGreen)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {

            // 👣 Circular walking icon
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(72.dp)
                    .background(deepGreen, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.DirectionsWalk,
                    contentDescription = "Footprint icon",
                    tint = Color.White,
                    modifier = Modifier.size(38.dp)
                )
            }

            // 🔠 Title
            Text(
                text = "StepQuest",
                color = deepGreen,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            // 💬 Subtitle
            Text(
                text = "Transform your steps into adventures.\nExplore, compete, and conquer challenges with friends.",
                color = forestGreen.copy(alpha = 0.9f),
                fontSize = 15.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            // 🧭 Feature grid (2x2)
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureBox(
                        icon = Icons.Default.Explore,
                        text = "Explore Maps",
                        iconColor = deepGreen,
                        textColor = darkGreen,
                        boxColor = paleWhiteGreen,
                        modifier = Modifier.weight(1f)
                    )
                    FeatureBox(
                        icon = Icons.Default.Group,
                        text = "Team Up",
                        iconColor = deepGreen,
                        textColor = darkGreen,
                        boxColor = paleWhiteGreen,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureBox(
                        icon = Icons.Default.Bolt,
                        text = "Earn Energy",
                        iconColor = deepGreen,
                        textColor = darkGreen,
                        boxColor = paleWhiteGreen,
                        modifier = Modifier.weight(1f)
                    )
                    FeatureBox(
                        icon = Icons.Default.EmojiEvents,
                        text = "Win Badges",
                        iconColor = deepGreen,
                        textColor = darkGreen,
                        boxColor = paleWhiteGreen,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 🚪 Buttons section
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Sign in button (deep green)
                Button(
                    onClick = { navController.navigate("signin") },
                    interactionSource = interactionSource,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text("Sign in ", fontWeight = FontWeight.Medium)
                }

                // Continue with email navigates to SignInScreen
                OutlinedButton(
                    onClick = { navController.navigate("signup") },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = forestGreen
                    ),
                    shape = RoundedCornerShape(28.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                ) {
                    Text("Create an account", fontWeight = FontWeight.Medium)
                }

                Text(
                    text = "By continuing, you agree to sync your step data with Google Fit",
                    color = forestGreen.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}


@Composable
fun FeatureBox(
    icon: ImageVector,
    text: String,
    iconColor: Color,
    textColor: Color,
    boxColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(boxColor, shape = RoundedCornerShape(24.dp))
            .padding(vertical = 20.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = iconColor,
            modifier = Modifier.size(34.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text,
            color = textColor.copy(alpha = 0.9f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

