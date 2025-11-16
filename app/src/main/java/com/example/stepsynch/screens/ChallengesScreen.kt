package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.navigation.NavController
import kotlin.math.roundToInt

@Composable
fun ChallengesScreen(navController: NavController) {
    // Color palette (match your earlier palette)
    val bgTop = Color(0xFFF8FAF6)
    val bgBottom = Color(0x1995B46A) // semi-transparent
    val gradientBackground = Brush.verticalGradient(listOf(bgTop, bgBottom))

    val primary = Color(0xFF3E5622)
    val midGreen = Color(0xFF709255)
    val softGreen = Color(0xFF95B46A)
    val accentOlive = Color(0xFF83781B)
    val titleDark = Color(0xFF172815)
    val muted = Color(0x993E5622)
    val deepGreen = Color(0xFF3E5622)

    // Sample data (same as your JS version)
    val activeChallenges = remember {
        listOf(
            ChallengeType(
                id = 1,
                name = "Weekend Warrior",
                description = "Walk 15,000 steps on Saturday",
                type = "daily",
                progress = 8234,
                target = 15000,
                reward = "Bronze Medal",
                badgeIcon = "🥉",
                timeLeft = "2 days",
                energyBonus = 500
            ),
            ChallengeType(
                id = 2,
                name = "Team Explorer",
                description = "Complete 3 regions with your team",
                type = "team",
                progress = 1,
                target = 3,
                reward = "Team Explorer Badge",
                badgeIcon = "🗺️",
                timeLeft = "5 days",
                energyBonus = 1000
            ),
            ChallengeType(
                id = 3,
                name = "Morning Mover",
                description = "Walk 5,000 steps before 10 AM",
                type = "daily",
                progress = 3456,
                target = 5000,
                reward = "Early Bird Badge",
                badgeIcon = "🌅",
                timeLeft = "Today",
                energyBonus = 300
            )
        )
    }

    val availableChallenges = remember {
        listOf(
            AvailableChallenge(
                id = 4,
                name = "10K Champion",
                description = "Reach 10,000 steps every day for a week",
                type = "weekly",
                target = 7,
                difficulty = "Medium",
                reward = "Consistency Master",
                badgeIcon = "⭐",
                energyBonus = 2000,
                participants = 1234
            ),
            AvailableChallenge(
                id = 5,
                name = "Ultra Walker",
                description = "Walk 50,000 steps in a single day",
                type = "special",
                target = 50000,
                difficulty = "Hard",
                reward = "Ultra Badge",
                badgeIcon = "💎",
                energyBonus = 5000,
                participants = 89
            ),
            AvailableChallenge(
                id = 6,
                name = "Social Butterfly",
                description = "Add 5 new friends this week",
                type = "social",
                target = 5,
                difficulty = "Easy",
                reward = "Community Star",
                badgeIcon = "🦋",
                energyBonus = 800,
                participants = 567
            )
        )
    }

    val completedChallenges = remember {
        listOf(
            CompletedChallenge(
                id = 7,
                name = "First Steps",
                description = "Complete your first day",
                completedDate = "Nov 15, 2025",
                reward = "Welcome Badge",
                badgeIcon = "👋"
            ),
            CompletedChallenge(
                id = 8,
                name = "Daily Streak",
                description = "Walk 5 days in a row",
                completedDate = "Nov 18, 2025",
                reward = "Streak Starter",
                badgeIcon = "🔥"
            )
        )
    }

    // Tab state
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Active (${activeChallenges.size})", "Available", "Completed (${completedChallenges.size})")


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // --- Main content ---
            Column(modifier = Modifier.fillMaxSize()) {

                // --- Header (Option B: Larger header like React) ---
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp) // push the row lower on the screen
                        .background(Color.White)
                        .border(1.dp, Color(0x103E5622))
                        .padding(horizontal = 20.dp, vertical = 40.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Challenges",
                                color = titleDark,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Complete challenges, earn badges",
                                color = muted,
                                fontSize = 14.sp
                            )
                        }

                        // Trophy circle (large, gradient)
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(28.dp))
                                .background(Brush.linearGradient(listOf(accentOlive, softGreen)))
                                .shadow(6.dp, RoundedCornerShape(28.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = "Trophy",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }

                // --- Tabs Row ---
                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    containerColor = Color.White,
                    contentColor = primary,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[selectedTab])
                                .height(3.dp),
                            color = primary
                        )
                    }
                ) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = title,
                                color = if (selectedTab == index) primary else muted,
                                modifier = Modifier.padding(vertical = 12.dp),
                                fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                // --- Content area (scrollable) ---
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedTab) {
                        0 -> ActiveTabContent(
                            items = activeChallenges,
                            primary = primary,
                            midGreen = midGreen,
                            accentOlive = accentOlive,
                            titleDark = titleDark,
                            muted = muted
                        )
                        1 -> AvailableTabContent(
                            items = availableChallenges,
                            primary = primary,
                            midGreen = midGreen,
                            accentOlive = accentOlive,
                            titleDark = titleDark,
                            muted = muted,
                            onJoinClick = { /* TODO: join action */ }
                        )
                        2 -> CompletedTabContent(
                            items = completedChallenges,
                            primary = primary,
                            midGreen = midGreen,
                            accentOlive = accentOlive,
                            titleDark = titleDark,
                            muted = muted
                        )
                    }
                }

                // --- Bottom gradient bar ---
                Box(
                    modifier = Modifier
                        .height(6.dp)
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(listOf(primary, midGreen, softGreen))
                        )
                )
            }

            // --- Back arrow overlay ---
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = deepGreen,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

/* ----------------------
   --- Tab contents ---
   ---------------------- */

@Composable
private fun ActiveTabContent(
    items: List<ChallengeType>,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { challenge ->
            ActiveChallengeCard(
                challenge = challenge,
                primary = primary,
                midGreen = midGreen,
                accentOlive = accentOlive,
                titleDark = titleDark,
                muted = muted
            )
        }
    }
}

@Composable
private fun AvailableTabContent(
    items: List<AvailableChallenge>,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color,
    onJoinClick: (AvailableChallenge) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { challenge ->
            AvailableChallengeCard(
                challenge = challenge,
                primary = primary,
                midGreen = midGreen,
                accentOlive = accentOlive,
                titleDark = titleDark,
                muted = muted,
                onJoinClick = onJoinClick
            )
        }
    }
}

@Composable
private fun CompletedTabContent(
    items: List<CompletedChallenge>,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { challenge ->
            CompletedChallengeCard(
                challenge = challenge,
                primary = primary,
                midGreen = midGreen,
                accentOlive = accentOlive,
                titleDark = titleDark,
                muted = muted
            )
        }

        if (items.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0x335DAD6F)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = Color(0x553E5622))
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("No completed challenges yet", color = muted)
                        Text("Start a challenge to earn your first badge!", color = muted, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }
    }
}

/* ----------------------
   --- Individual Cards ---
   ---------------------- */

@Composable
private fun ActiveChallengeCard(
    challenge: ChallengeType,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color
) {
    val progressFraction = (challenge.progress.toFloat() / challenge.target.toFloat()).coerceIn(0f, 1f)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                // badge icon box
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Brush.linearGradient(listOf(midGreen.copy(alpha = 0.2f), midGreen.copy(alpha = 0.1f)))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(challenge.badgeIcon, fontSize = 24.sp)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(challenge.name, color = titleDark, fontWeight = FontWeight.Bold)
                        BadgeComposable(text = challenge.timeLeft, tint = accentOlive)
                    }

                    Text(challenge.description, color = muted, modifier = Modifier.padding(top = 6.dp))
                    Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(getTypeIcon(challenge.type), contentDescription = null, tint = Color(0x993E5622), modifier = Modifier.size(18.dp))
                        Text(challenge.type.capitalize(), color = muted)
                    }
                }
            }

            // progress area
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("${challenge.progress} / ${challenge.target}", color = Color(0xFF3E5622))
                    Text("${(progressFraction * 100).roundToInt()}%", color = midGreen, fontWeight = FontWeight.Bold)
                }
                LinearProgressIndicator(
                    progress = progressFraction,
                    color = midGreen,
                    trackColor = Color(0x113E5622),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .padding(top = 8.dp)
                )
            }

            // rewards row
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = accentOlive, modifier = Modifier.size(18.dp))
                    Text(challenge.reward, color = titleDark)
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(Icons.Default.Bolt, contentDescription = null, tint = accentOlive, modifier = Modifier.size(18.dp))
                    Text("+${challenge.energyBonus}", color = accentOlive)
                }
            }
        }
    }
}

@Composable
private fun AvailableChallengeCard(
    challenge: AvailableChallenge,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color,
    onJoinClick: (AvailableChallenge) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Brush.linearGradient(listOf(midGreen.copy(alpha = 0.2f), midGreen.copy(alpha = 0.1f)))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(challenge.badgeIcon, fontSize = 24.sp)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(challenge.name, color = titleDark, fontWeight = FontWeight.Bold)
                        DifficultyBadge(difficulty = challenge.difficulty)
                    }

                    Text(challenge.description, color = muted, modifier = Modifier.padding(top = 6.dp))

                    Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(getTypeIcon(challenge.type), contentDescription = null, tint = Color(0x993E5622), modifier = Modifier.size(16.dp))
                            Text(challenge.type.capitalize(), color = muted)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(Icons.Default.Group, contentDescription = null, tint = Color(0x993E5622), modifier = Modifier.size(16.dp))
                            Text("${challenge.participants} joined", color = muted)
                        }
                    }
                }
            }

            // target info
            Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFF8FAF6), shape = RoundedCornerShape(10.dp)).padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Explore, contentDescription = null, tint = Color(0xFF3E5622), modifier = Modifier.size(16.dp))
                    val targetLabel = if (challenge.type == "weekly") "days" else "steps"
                    Text("Target: ${challenge.target} $targetLabel", color = Color(0xFF3E5622))
                }
            }

            // rewards + action
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = accentOlive, modifier = Modifier.size(16.dp))
                        Text(challenge.reward, color = titleDark)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Icon(Icons.Default.Bolt, contentDescription = null, tint = accentOlive, modifier = Modifier.size(16.dp))
                        Text("+${challenge.energyBonus} energy", color = accentOlive)
                    }
                }

                Button(
                    onClick = { onJoinClick(challenge) },
                    colors = ButtonDefaults.buttonColors(containerColor = primary, contentColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(44.dp)
                ) {
                    Text("Join Challenge")
                }
            }
        }
    }
}

@Composable
private fun CompletedChallengeCard(
    challenge: CompletedChallenge,
    primary: Color,
    midGreen: Color,
    accentOlive: Color,
    titleDark: Color,
    muted: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Brush.linearGradient(listOf(midGreen.copy(alpha = 0.2f), soften(midGreen)))),
                contentAlignment = Alignment.Center
            ) {
                Text(challenge.badgeIcon, fontSize = 20.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(challenge.name, color = titleDark, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = midGreen, modifier = Modifier.size(18.dp))
                }
                Text(challenge.description, color = muted)
                Row(modifier = Modifier.padding(top = 6.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = midGreen, modifier = Modifier.size(14.dp))
                    Text(challenge.reward, color = midGreen)
                    Text("• ${challenge.completedDate}", color = muted)
                }
            }
        }
    }
}

/* ----------------------
   --- Small UI pieces ---
   ---------------------- */

@Composable
private fun BadgeComposable(text: String, tint: Color) {
    Box(
        modifier = Modifier
            .background(tint.copy(alpha = 0.12f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(Icons.Default.AccessTime, contentDescription = null, tint = tint, modifier = Modifier.size(14.dp))
            Text(text, color = Color(0xFF172815), fontSize = 12.sp)
        }
    }
}

@Composable
private fun DifficultyBadge(difficulty: String) {
    val color = when (difficulty) {
        "Easy" -> Color(0xFF709255)
        "Medium" -> Color(0xFF83781B)
        "Hard" -> Color(0xFF3E5622)
        else -> Color(0xFF95B46A)
    }
    Box(
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(difficulty, color = Color.White, fontSize = 12.sp)
    }
}

/* ----------------------
   --- Helpers & models ---
   ---------------------- */

private fun getTypeIcon(type: String): ImageVector {
    return when (type) {
        "daily" -> Icons.Default.CalendarToday
        "weekly" -> Icons.Default.Task ?: Icons.Default.Flag // fallback
        "team", "social" -> Icons.Default.Group
        else -> Icons.Default.EmojiEvents
    }
}

private fun soften(c: Color): Color {
    // produce a slightly lighter version for subtle gradients
    return c.copy(alpha = 0.18f)
}

// Data classes used by the screen
private data class ChallengeType(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val progress: Int,
    val target: Int,
    val reward: String,
    val badgeIcon: String,
    val timeLeft: String,
    val energyBonus: Int
)

private data class AvailableChallenge(
    val id: Int,
    val name: String,
    val description: String,
    val type: String,
    val target: Int,
    val difficulty: String,
    val reward: String,
    val badgeIcon: String,
    val energyBonus: Int,
    val participants: Int
)

private data class CompletedChallenge(
    val id: Int,
    val name: String,
    val description: String,
    val completedDate: String,
    val reward: String,
    val badgeIcon: String
)
