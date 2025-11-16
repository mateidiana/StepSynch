package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun OnboardScreen(navController: NavController) {

    val slides = listOf(
        SlideData(
            icon = Icons.Default.DirectionsWalk,
            title = "Track Your Steps",
            description = "Sync with Google Fit and let every step count towards your adventure",
            gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255)))
        ),
        SlideData(
            icon = Icons.Default.Bolt,
            title = "Earn Energy Points",
            description = "Transform your daily steps into energy points that power your exploration",
            gradient = Brush.linearGradient(listOf(Color(0xFF709255), Color(0xFF83781B)))
        ),
        SlideData(
            icon = Icons.Default.Map,
            title = "Explore New Worlds",
            description = "Unlock beautiful 2D maps of cities and nature as you reach new milestones",
            gradient = Brush.linearGradient(listOf(Color(0xFF83781B), Color(0xFF95B46A)))
        ),
        SlideData(
            icon = Icons.Default.Group,
            title = "Team Up with Friends",
            description = "Join teams and complete co-op missions together for exclusive rewards",
            gradient = Brush.linearGradient(listOf(Color(0xFF95B46A), Color(0xFF3E5622)))
        ),
        SlideData(
            icon = Icons.Default.EmojiEvents,
            title = "Earn Badges & Compete",
            description = "Complete challenges, climb leaderboards, and collect unique achievement badges",
            gradient = Brush.linearGradient(listOf(Color(0xFF709255), Color(0xFF172815)))
        )
    )

    var currentSlide by remember { mutableStateOf(0) }

    val lightBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFF8FAF6), Color(0x3395B46A))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {

        // Skip Button
        if (currentSlide < slides.lastIndex) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Skip",
                    color = Color(0xFF3E5622),
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        currentSlide = slides.lastIndex
                    }
                )
            }
        } else {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 28.dp)
                .let { if (currentSlide == slides.lastIndex) it.padding(top = 80.dp) else it },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Octagon icon container
            OctagonIconBox(slides[currentSlide].gradient) {
                Icon(
                    imageVector = slides[currentSlide].icon,
                    contentDescription = slides[currentSlide].title,
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Title & description
            Text(
                text = slides[currentSlide].title,
                color = Color(0xFF172815),
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = slides[currentSlide].description,
                color = Color(0xFF3E5622).copy(alpha = 0.7f),
                fontSize = 16.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(horizontal = 12.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Pagination Dots
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                slides.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .width(if (index == currentSlide) 26.dp else 10.dp)
                            .background(
                                if (index == currentSlide)
                                    Color(0xFF3E5622)
                                else
                                    Color(0xFF3E5622).copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(3.dp)
                            .clickable { currentSlide = index }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            if (currentSlide < slides.lastIndex) {
                Button(
                    onClick = { currentSlide++ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3E5622),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text("Next", fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Next",
                        tint = Color.White
                    )
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { navController.navigate("home") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3E5622),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text("Connect Google Fit", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Required to track your steps",
                        color = Color(0xFF3E5622).copy(alpha = 0.5f),
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Bottom gradient bar
        Box(
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF3E5622),
                            Color(0xFF709255),
                            Color(0xFF95B46A)
                        )
                    )
                )
        )
    }
}

// --- Octagon Shape Container ---
@Composable
fun OctagonIconBox(gradient: Brush, content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .size(130.dp)
            .shadow(16.dp, RoundedCornerShape(24.dp))
            .background(gradient, shape = RoundedCornerShape(24.dp)), // approximates octagon
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

data class SlideData(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val title: String,
    val description: String,
    val gradient: Brush
)