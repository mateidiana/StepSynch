package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class LeaderboardUser(
    val id: Int,
    val rank: Int,
    val name: String,
    val initials: String,
    val steps: Int,
    val energy: Int,
    val trend: String? = null, // "up", "down", "same"
    val change: String? = null,
    val isCurrentUser: Boolean = false,
    val avgDaily: Int? = null,
    val streak: Int? = null
)

@Composable
fun LeaderboardScreen(navController: NavController) {
    val todayLeaderboard = remember {
        listOf(
            LeaderboardUser(1, 1, "Sarah Chen", "SC", 15234, 1523, "up", "+2", streak = 12),
            LeaderboardUser(2, 2, "Mike Johnson", "MJ", 14876, 1487, "same", "0", streak = 8),
//            LeaderboardUser(3, 3, "Alex Thompson", "AT", 12543, 1254, "up", "+1", isCurrentUser = true, streak = 7),
            LeaderboardUser(4, 3, "Emma Davis", "ED", 11234, 1123, "down", "-1", streak = 15),
            LeaderboardUser(5, 4, "James Wilson", "JW", 10987, 1098, "same", "0", streak = 5),
            LeaderboardUser(6, 5, "Lisa Anderson", "LA", 9876, 987, "up", "+2", streak = 9),
            LeaderboardUser(7, 6, "Tom Brown", "TB", 8234, 823, "down", "-3", streak = 3),
        )
    }

    val weeklyLeaderboard = remember {
        listOf(
            LeaderboardUser(1, 1, "Sarah Chen", "SC", 89234, 8923, avgDaily = 12748, streak = 12),
            LeaderboardUser(2, 2, "Emma Davis", "ED", 82543, 8254, avgDaily = 11792, streak = 15),
            //LeaderboardUser(3, 3, "Alex Thompson", "AT", 78234, 7823, avgDaily = 11176, isCurrentUser = true, streak = 7),
            LeaderboardUser(4, 3, "Mike Johnson", "MJ", 75432, 7543, avgDaily = 10776, streak = 8),
            LeaderboardUser(5, 4, "Lisa Anderson", "LA", 68234, 6823, avgDaily = 9748, streak = 9)
        )
    }

    var selectedTab by remember { mutableStateOf("today") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFF8FAF6), Color(0xFF95B46A).copy(alpha = 0.1f))))
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF172815)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text("Leaderboard", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF172815), modifier = Modifier.padding(top = 30.dp))
                    Text("See how you rank among friends", fontSize = 14.sp, color = Color(0xFF3E5622).copy(alpha = 0.7f))
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(Color(0xFF83781B), Color(0xFF95B46A)))),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🏆", fontSize = 24.sp)
                }
            }
        }

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            TabButtonPage("Today", selected = selectedTab == "today") { selectedTab = "today" }
            TabButtonPage("This Week", selected = selectedTab == "week") { selectedTab = "week" }
            TabButtonPage("All Time", selected = selectedTab == "alltime") { selectedTab = "alltime" }
        }

        Spacer(modifier = Modifier.height(8.dp))

        when (selectedTab) {
            "today" -> LeaderboardList(todayLeaderboard)
            "week" -> LeaderboardList(weeklyLeaderboard)
            "alltime" -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🏆", fontSize = 32.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("All-time rankings coming soon!", color = Color(0xFF3E5622).copy(alpha = 0.7f))
                        Text("Keep walking to climb the ranks", color = Color(0xFF3E5622).copy(alpha = 0.5f))
                    }
                }
            }
        }

        // Bottom gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Brush.horizontalGradient(listOf(Color(0xFF3E5622), Color(0xFF709255), Color(0xFF95B46A))))
        )
    }
}

@Composable
fun TabButtonPage(text: String, selected: Boolean, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = if (selected) Color(0xFF3E5622) else Color(0xFF3E5622).copy(alpha = 0.7f)
        )
    ) {
        Text(text, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun LeaderboardList(users: List<LeaderboardUser>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users) { user ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    // Rank
                    Text(
                        text = "#${user.rank}",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(32.dp)
                    )

                    // Avatar
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF95B46A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(user.initials, color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(user.name, fontWeight = FontWeight.Bold, color = Color(0xFF172815))
//                            if (user.isCurrentUser) {
//                                Spacer(modifier = Modifier.width(4.dp))
//                                Text("You", color = Color.White, modifier = Modifier.background(Color(0xFF3E5622), RoundedCornerShape(4.dp)).padding(2.dp))
//                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${user.steps} steps", color = Color(0xFF3E5622).copy(alpha = 0.7f))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(horizontalAlignment = Alignment.End) {
                        if (user.trend != null) {
                            Text("${user.trend} ${user.change}", color = if (user.trend == "up") Color(0xFF709255) else if (user.trend == "down") Color(0xFFd4183d) else Color(0xFF3E5622))
                        }
                        Text("${user.energy}", color = Color(0xFF83781B))
                    }
                }
            }
        }
    }
}
