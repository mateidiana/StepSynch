package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavController
import com.example.stepsynch.repository.AuthRepository
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController, authRepository: AuthRepository) {
    val currentUser by authRepository.currentUser.collectAsState()
    var username by remember { mutableStateOf<String?>(null) }
    var currentSteps by remember { mutableStateOf(0) }
    var dailyGoal by remember { mutableStateOf(10000) }
    var currentEnergy by remember { mutableStateOf(0) }
    var streak by remember { mutableStateOf(0) }

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let { uid ->
            authRepository.getUser(uid) { user ->
                username = user?.username
            }
            authRepository.getUserStats(uid) { stats ->
                stats?.let {
                    currentSteps = it.steps
                    dailyGoal = it.dailyGoal
                    currentEnergy = it.energy
                    streak = it.streak
                }
            }
        }

    }


    val stepProgress = (currentSteps / dailyGoal.toFloat()) * 100


    val quickStats = listOf(
        QuickStat("Weekly Avg", "9,450", Icons.Default.TrendingUp, Color(0xFF709255)),
        QuickStat("This Week", "58,234", Icons.Default.DirectionsWalk, Color(0xFF95B46A)),
        QuickStat("Active Challenges", "3", Icons.Default.Task, Color(0xFF83781B)),
        QuickStat("Badges Earned", "2", Icons.Default.EmojiEvents, Color(0xFF3E5622)),
    )

    val activeChallenges = listOf(
        Challenge(
            "Weekend Warrior",
            "Walk 15,000 steps on Saturday",
            progress = 8234,
            target = 15000,
            reward = "Bronze Medal",
            timeLeft = "2 days left"
        ),
        Challenge(
            "Team Explorer",
            "Complete 3 regions with your team",
            progress = 1,
            target = 3,
            reward = "Team Badge",
            timeLeft = "5 days left"
        )
    )

    val lightBackground = Brush.verticalGradient(listOf(Color(0xFFF8FAF6), Color(0x3395B46A)))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 24.dp, end = 24.dp, top = 45.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Welcome back!", color = Color(0x993E5622))
                Text(
                    username ?: "User",
                    color = Color(0xFF172815),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GradientBadge(
                    icon = Icons.Default.Bolt,
                    value = currentEnergy.toString(),
                    gradient = Brush.linearGradient(listOf(Color(0xFF83781B), Color(0xFF95B46A)))
                )
                GradientBadge(
                    icon = Icons.Default.Whatshot,
                    value = streak.toString(),
                    gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255)))
                )
            }
        }

        // Main Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(start = 24.dp, end = 24.dp, top = 32.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            // Daily Steps Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("Today's Progress", color = Color(0xFF172815), fontWeight = FontWeight.Bold)
                            Text("Keep moving to reach your goal!", color = Color(0x993E5622))
                        }
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(
                                    brush = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.DirectionsWalk, contentDescription = null, tint = Color.White)
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("${currentSteps} / $dailyGoal steps", color = Color(0xFF172815), fontWeight = FontWeight.Bold)
                        Text("${stepProgress.roundToInt()}%", color = Color(0xFF709255), fontWeight = FontWeight.Bold)
                    }
                    LinearProgressIndicator(
                        progress = stepProgress / 100f,
                        color = Color(0xFF709255),
                        trackColor = Color(0x333E5622),
                        modifier = Modifier
                            .height(10.dp)
                            .clip(RoundedCornerShape(6.dp))
                    )

                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        StatItem(label = "Calories", value = "245 kcal")
                        StatItem(label = "Distance", value = "6.2 km")
                        StatItem(label = "Energy Earned", value = "+823", valueColor = Color(0xFF83781B))
                    }
                }
            }

            @OptIn(ExperimentalLayoutApi::class)
            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                quickStats.forEach { stat ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(stat.color.copy(alpha = 0.2f), shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(stat.icon, contentDescription = null, tint = Color(0xFF3E5622))
                            }
                            Text(stat.label, color = Color(0x993E5622))
                            Text(stat.value, color = Color(0xFF172815), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }


            // Active Challenges
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("Active Challenges", fontWeight = FontWeight.Bold, color = Color(0xFF172815))
                    Text("   View All", color = Color(0xFF3E5622), modifier = Modifier.clickable { navController.navigate("challenges") })
                }

                activeChallenges.forEach { challenge ->
                    val progress = (challenge.progress.toFloat() / challenge.target) * 100
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                        Text(challenge.name, fontWeight = FontWeight.Bold, color = Color(0xFF172815))
                                        Badge(text = challenge.timeLeft)
                                    }
                                    Text(challenge.description, color = Color(0x993E5622))
                                }
                            }

                            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                                Text("${challenge.progress} / ${challenge.target}", color = Color(0xFF3E5622))
                                Text("${progress.roundToInt()}%", color = Color(0xFF709255))
                            }

                            LinearProgressIndicator(
                                progress = progress / 100f,
                                color = Color(0xFF709255),
                                trackColor = Color(0x333E5622),
                                modifier = Modifier
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(6.dp))
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color(0xFF83781B))
                                Text("Reward: ${challenge.reward}", color = Color(0xFF172815))
                            }
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // --- Row 1 ---
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                    GradientButton(
                        text = "Explore Map",
                        icon = Icons.Default.Map,
                        gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                        modifier = Modifier.weight(1f),
                        onClick = { /*TO DO*/ }
                    )
                    GradientButton(
                        text = "View Profile",
                        icon = Icons.Default.Person,
                        gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("profile") }
                    )
                }

                // --- Row 2 ---
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                    GradientButton(
                        text = "View Friends",
                        icon = Icons.Default.Group,
                        gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("friends") }
                    )
                    GradientButton(
                        text = "View Leaderboard",
                        icon = Icons.Default.EmojiEvents,
                        gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("leaderboard") }
                    )
                }

                // --- Row 3 ---
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    GradientButton(
                        text = "View Challenges",
                        icon = Icons.Default.Task,
                        gradient = Brush.linearGradient(listOf(Color(0xFF3E5622), Color(0xFF709255))),
                        modifier = Modifier.width(160.dp),
                        onClick = { navController.navigate("challenges") }

                    )
//                    Spacer(modifier = Modifier.weight(1f)) // empty space to balance the row
                }
            }


            Spacer(modifier = Modifier.height(24.dp)) // extra bottom padding
        }
    }
}

// --- Supporting Composables ---
@Composable
fun GradientBadge(icon: ImageVector, value: String, gradient: Brush) {
    Box(
        modifier = Modifier
            .background(gradient, shape = RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
            Text(value, color = Color.White, fontSize = 14.sp)
        }
    }
}

@Composable
fun StatItem(label: String, value: String, valueColor: Color = Color(0xFF172815)) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = Color(0x993E5622))
        Text(value, color = valueColor, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun Badge(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0x3373781B), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text, color = Color(0xFF172815), fontSize = 12.sp)
    }
}

@Composable
fun GradientButton(text: String, icon: ImageVector, gradient: Brush, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Icon(icon, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text, color = Color.White)
            }
        }
    }
}

// --- Data Classes ---
data class QuickStat(val label: String, val value: String, val icon: ImageVector, val color: Color)
data class Challenge(val name: String, val description: String, val progress: Int, val target: Int, val reward: String, val timeLeft: String)
