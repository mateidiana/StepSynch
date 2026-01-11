package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stepsynch.models.UserStatsGF
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.repository.AuthRepository

@Composable
fun ProfileScreen(navController: NavController, authRepository: AuthRepository) {
    val currentUser by authRepository.currentUser.collectAsState()
    var username by remember { mutableStateOf<String?>(null) }
    var stats by remember { mutableStateOf<UserStatsGF?>(null) }
    var gameStats by remember { mutableStateOf<UserStatsGame?>(null) }
    var completedRegionsCount by remember { mutableStateOf(0) }
    var teamName by remember { mutableStateOf("No team yet") }
    var earnedBadges by remember { mutableStateOf<List<AvailableChallenge>>(emptyList()) }
    var activeChallengesCount by remember { mutableStateOf(0) }
    var earnedBadgesCount by remember { mutableStateOf(0) }

    val availableChallenges = remember {
        listOf(
            AvailableChallenge(
                id = 1,
                name = "First Steps",
                description = "Complete your first day",
                type = "daily",
                target = 2000,
                difficulty = "Easy",
                reward = "Welcome Badge",
                badgeIcon = "👋",
                energyBonus = 150
            ),
            AvailableChallenge(
                id = 2,
                name = "Daily Streak",
                description = "Walk 5 days in a row",
                type = "daily",
                target = 5,
                difficulty = "Easy",
                reward = "Streak Starter",
                badgeIcon = "🔥",
                energyBonus = 300
            ),
            AvailableChallenge(
                id = 3,
                name = "10K Champion",
                description = "Reach 10,000 steps every day for a week",
                type = "weekly",
                target = 7,
                difficulty = "Medium",
                reward = "Consistency Master",
                badgeIcon = "⭐",
                energyBonus = 2000,
            ),
            AvailableChallenge(
                id = 4,
                name = "Ultra Walker",
                description = "Walk 50,000 steps in a single day",
                type = "special",
                target = 50000,
                difficulty = "Hard",
                reward = "Ultra Badge",
                badgeIcon = "💎",
                energyBonus = 5000,
            ),
            AvailableChallenge(
                id = 5,
                name = "Social Butterfly",
                description = "Add 5 new friends",
                type = "social",
                target = 5,
                difficulty = "Easy",
                reward = "Community Star",
                badgeIcon = "🦋",
                energyBonus = 500,
            ),
            AvailableChallenge(
                id = 6,
                name = "Team explorer",
                description = "Join 1 team",
                type = "social",
                target = 5,
                difficulty = "Easy",
                reward = "Team badge",
                badgeIcon = "🗺️",
                energyBonus = 300,
            )
        )
    }

    val teams = remember {
        listOf(
            Team(1, "The Steppers", 0, 0, Color.Unspecified)
        )
    }

    val creationDate = currentUser?.metadata?.creationTimestamp
        ?.let { timestamp ->
            java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
                .format(java.util.Date(timestamp))
        }

    LaunchedEffect(currentUser) {
        currentUser?.uid?.let { uid ->
            authRepository.getUser(uid) { user ->
                username = user?.username
            }
            authRepository.getUserStats(uid) { userStats ->
                stats = userStats
            }
            authRepository.getUserGameStats(uid) { userGameStats ->
                gameStats = userGameStats
            }
            authRepository.getCompletedRegionCount(uid) { count ->
                completedRegionsCount = count
            }
            authRepository.getUserTeam(uid) { teamId ->
                teamName = teams.firstOrNull { it.id == teamId }?.name
                    ?: "No team yet"
            }
            authRepository.getCompletedChallenges(uid) { completedIds ->
                // Match completed IDs with availableChallenges
                earnedBadges = availableChallenges.filter { it.id in completedIds }
            }
            authRepository.getActiveChallengeCount(uid) {
                activeChallengesCount = it
            }

            authRepository.getCompletedChallengeCount(uid) {
                earnedBadgesCount = it
            }
        }
    }

    val userStats = object {

            val name = username ?: "Loading"
            //val initials = "PIC"
            val initials: String = username?.split(" ")?.filter { it.isNotEmpty() }
                ?.map { it.first().uppercaseChar() }?.joinToString("")?.take(2)
                ?: "??"
            val totalSteps = gameStats?.totalSteps ?: 0
            val totalEnergy = gameStats?.totalEnergyPoints ?: 0
            val streak = stats?.streak ?: 0
            val rank = gameStats?.rank ?: 0
            val joinedDate = creationDate.toString()
            val teamName = teamName
            val activeChallenges = activeChallengesCount
            val badgesEarned = earnedBadgesCount

    }

    val completedChallenges = remember {
        listOf(
            Triple("First Steps", "Nov 15, 2025", 5000),
            Triple("Daily Streak", "Nov 18, 2025", 25000)
        )
    }


    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFF8FAF6), Color(0xFF95B46A).copy(alpha = 0.1f))
    )

    // ---------------- ROOT LAYOUT ----------------
    Surface(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.fillMaxSize()) {

            // ---------------- SCROLLABLE CONTENT ----------------
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBackground)
                    .verticalScroll(rememberScrollState())
            ) {

                // ---------------- HEADER ----------------
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xFF3E5622), Color(0xFF709255))
                            )
                        )
                ) {

                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }


                    // Top row with avatar + settings button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 65.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    userStats.initials,
                                    color = Color(0xFF3E5622),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(
                                    userStats.name,
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(4.dp))

                                Row {
                                    Badge(
                                        backgroundColor = Color(0xFF83781B),
                                        textColor = Color.White,
                                        text = "Rank #${userStats.rank}",
                                        icon = Icons.Default.EmojiEvents
                                    )
                                }
                            }
                        }

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                // ---------------- QUICK STATS GRID ----------------
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    items(4) { index ->

                        val stat = when (index) {
                            0 -> Triple("Total Steps", userStats.totalSteps.toString(), Icons.Default.DirectionsWalk)
                            1 -> Triple("Total Energy", userStats.totalEnergy.toString(), Icons.Default.Bolt)
                            2 -> Triple("Badges Earned", userStats.badgesEarned.toString(), Icons.Default.Star)
                            else -> Triple("Active Challenges", userStats.activeChallenges.toString(), Icons.Default.EmojiEvents)
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF3E5622)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(stat.third, contentDescription = stat.first, tint = Color.White)
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(stat.first, fontSize = 12.sp, color = Color(0xFF3E5622))
                                Text(
                                    stat.second,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color(0xFF172815)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))


                // ---------------- PERSONAL INFO ----------------
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 5.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Personal Info", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow("Member Since", userStats.joinedDate, Icons.Default.CalendarToday)
                        InfoRow("Team", userStats.teamName, Icons.Default.Group)
                        InfoRow("Current Streak", "${userStats.streak} days", Icons.Default.TrendingUp)
                        InfoRow("Regions Explored", "$completedRegionsCount / 10", Icons.Default.Place)
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))


                // ---------------- BADGES ----------------
                Text(
                    "Earned Badges (${earnedBadges.size})",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 25.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    items(earnedBadges) { badge ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF95B46A).copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(badge.badgeIcon, fontSize = 20.sp)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(badge.name, fontSize = 12.sp, textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                Spacer(modifier = Modifier.height(16.dp))


                // ---------------- ACTION BUTTONS ----------------
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = {
                            authRepository.logout()

                            navController.navigate("welcome") {
                                popUpTo(0) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF3E5622)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.TrackChanges, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Log out")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }


            // ---------------- BOTTOM 6DP GRADIENT BAR ----------------
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(6.dp)
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF3E5622), Color(0xFF709255), Color(0xFF95B46A))
                        )
                    )
            )
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF95B46A).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = label, tint = Color(0xFF3E5622))
            }
            Text(label, color = Color(0xFF3E5622))
        }
        Text(value, fontWeight = FontWeight.Bold, color = Color(0xFF172815))
    }
}

@Composable
fun Badge(backgroundColor: Color, textColor: Color, text: String, icon: ImageVector? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null, tint = textColor, modifier = Modifier.size(14.dp))
        }
        Text(text, color = textColor, fontSize = 12.sp)
    }
}


