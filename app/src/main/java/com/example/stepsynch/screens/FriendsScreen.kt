package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

data class Friend(
    val id: Int,
    val name: String,
    val initials: String,
    val steps: Int,
    val energy: Int,
    val streak: Int,
    val trend: String,
    val team: String?
)

data class Team(
    val id: Int,
    val name: String,
    val members: Int,
    val totalSteps: Int,
    val rank: Int,
    val color: Color
)

@Composable
fun FriendsScreen(navController: NavController) {
    val friends = remember {
        listOf(
            Friend(1, "Sarah Chen", "SC", 12543, 3200, 12, "+15%", "The Steppers"),
            Friend(2, "Mike Johnson", "MJ", 10234, 2850, 8, "+8%", "The Steppers"),
            Friend(3, "Emma Davis", "ED", 9876, 2400, 15, "+12%", null),
            Friend(4, "James Wilson", "JW", 8234, 2100, 5, "-3%", null),
            Friend(5, "Lisa Anderson", "LA", 7845, 1980, 9, "+5%", "The Steppers")
        )
    }

    val teams = remember {
        listOf(
            Team(1, "The Steppers", 3, 30622, 2, Color(0xFF3E5622))
        )
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFFF8FAF6), Color(0xFF95B46A).copy(alpha = 0.1f))
    )

    var selectedTab by remember { mutableStateOf("friends") } // ✅ fixed

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.navigateUp() }, modifier = Modifier.padding(start = 0.dp)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF172815))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Friends & Teams", fontSize = 20.sp, color = Color(0xFF172815))
                        Text(
                            "Stay connected, compete together",
                            fontSize = 14.sp,
                            color = Color(0xFF3E5622).copy(alpha = 0.7f)
                        )
                    }
                }

                Button(
                    onClick = { /* TODO: Add friend */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E5622), contentColor = Color.White)
                ) {
                    Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Add")
                }
            }

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search friends...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(8.dp)
            )
        }

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabButton("Friends (${friends.size})", selected = selectedTab == "friends") { selectedTab = "friends" }
            TabButton("Teams (${teams.size})", selected = selectedTab == "teams") { selectedTab = "teams" }
        }

        Spacer(Modifier.height(8.dp))

        if (selectedTab == "friends") {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(friends) { friend ->
                    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(Color(0xFF3E5622)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(friend.initials, color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(friend.name, fontWeight = FontWeight.Bold, color = Color(0xFF172815))
                                    friend.team?.let {
                                        Spacer(Modifier.width(6.dp))
                                        Badge(text = it)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    StatItem(Icons.Default.DirectionsWalk, friend.steps.toString())
                                    StatItem(Icons.Default.Bolt, friend.energy.toString())
                                    StatItem(
                                        if (friend.trend.startsWith("+")) Icons.Default.TrendingUp else Icons.Default.TrendingDown,
                                        friend.trend
                                    )
                                }
                            }
                            Spacer(Modifier.width(8.dp))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .background(Color(0xFF83781B)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("${friend.streak}", color = Color.White)
                                }
                                Text(
                                    "day streak",
                                    fontSize = 10.sp,
                                    color = Color(0xFF3E5622).copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(teams) { team ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = team.color),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(team.name, fontWeight = FontWeight.Bold, color = Color.White)
                                    Text(
                                        "${team.members} active members",
                                        color = Color.White.copy(alpha = 0.8f),
                                        fontSize = 12.sp
                                    )
                                }
                                Badge(text = "Rank #${team.rank}", whiteText = true)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("Team Steps Today: ${team.totalSteps}", color = Color.White.copy(alpha = 0.8f))
                            Spacer(Modifier.height(8.dp))
                            Button(
                                onClick = { /* TODO */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = team.color)
                            ) {
                                Text("View Team Details")
                            }
                        }
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
fun TabButton(text: String, selected: Boolean, onClick: () -> Unit) {
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
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp), tint = Color(0xFF3E5622))
        Text(value, fontSize = 12.sp, color = Color(0xFF3E5622).copy(alpha = 0.7f))
    }
}

@Composable
fun Badge(text: String, whiteText: Boolean = false) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .background(Color(0xFF709255).copy(alpha = 0.2f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(text, fontSize = 12.sp, color = if (whiteText) Color.White else Color(0xFF3E5622))
    }
}

