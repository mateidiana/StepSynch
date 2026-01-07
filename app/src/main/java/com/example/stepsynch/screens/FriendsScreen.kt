package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.stepsynch.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

data class Friend(
    val id: String,
    val name: String,
    val initials: String,
    val steps: Int,
    val energy: Int,
    val streak: Int
)

data class Team(
    val id: Int,
    val name: String,
    val members: Int,
    val totalSteps: Int,
    val color: Color
)

@Composable
fun FriendsScreen(navController: NavController, authRepository: AuthRepository) {
//    val people = remember {
//        listOf(
//            Friend(1, "Sarah Chen", "SC", 12543, 3200, 12),
//            Friend(2, "Mike Johnson", "MJ", 10234, 2850, 8),
//            Friend(3, "Emma Davis", "ED", 9876, 2400, 15),
//            Friend(4, "James Wilson", "JW", 8234, 2100, 5),
//            Friend(5, "Lisa Anderson", "LA", 7845, 1980, 9)
//        )
//    }

    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    var people by remember { mutableStateOf<List<Friend>>(emptyList()) }
    var selectedTab by remember { mutableStateOf("people") }

    // 🔹 Load users from Firestore
    LaunchedEffect(Unit) {
        authRepository.getAllUsers { users ->
            people = users
                .filter { it.uid != currentUserId }
                .map { user ->
                    Friend(
                        id = user.uid,
                        name = user.username,
                        initials = user.username
                            .split(" ")
                            .take(2)
                            .joinToString("") { it.first().uppercase() },
                        steps = 8_500,
                        energy = 2_100,
                        streak = 7
                    )
                }
        }
    }

    val teams = remember {
        listOf(
            Team(1, "The Steppers", 3, 30622, Color(0xFF3E5622))
        )
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFF8FAF6),
            Color(0xFF95B46A).copy(alpha = 0.1f)
        )
    )

    //var selectedTab by remember { mutableStateOf("people") }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Friends & Teams", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Stay connected, compete together",
                        fontSize = 14.sp,
                        color = Color(0xFF3E5622).copy(alpha = 0.7f)
                    )
                }
            }
        }

        // Tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabButton("Discover people", selectedTab == "people") { selectedTab = "people" }
            TabButton("Teams", selectedTab == "teams") { selectedTab = "teams" }
            TabButton("Friends", selectedTab == "friends") { selectedTab = "friends" }
            TabButton("Requests", selectedTab == "requests") { selectedTab = "requests" }
        }

        Spacer(Modifier.height(8.dp))

        when (selectedTab) {
            "people" -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(people) { friend ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
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
                                    Text(friend.name, fontWeight = FontWeight.Bold)
                                    Spacer(Modifier.height(4.dp))
                                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                        StatItem(Icons.Default.DirectionsWalk, friend.steps.toString())
                                        StatItem(Icons.Default.Bolt, friend.energy.toString())
                                    }
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
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

                                    // ✅ Add Friend button
                                    OutlinedButton(
                                        onClick = { },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                                        modifier = Modifier.height(28.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.PersonAdd,
                                            contentDescription = null,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        Text("Add", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            "teams" -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(teams) { team ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = team.color)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(team.name, fontWeight = FontWeight.Bold, color = Color.White)
                                Text("${team.members} active members", color = Color.White.copy(alpha = 0.8f))
                                Spacer(Modifier.height(8.dp))
                                Text("Team Steps Today: ${team.totalSteps}", color = Color.White.copy(alpha = 0.8f))
                            }
                        }
                    }
                }
            }

            "friends" -> EmptyState("No friends yet")
            "requests" -> EmptyState("No friend requests yet")
        }
    }
}

@Composable
fun TabButton(text: String, selected: Boolean, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(2.dp))
        Text(value, fontSize = 12.sp)
    }
}

@Composable
fun EmptyState(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text, fontSize = 16.sp, color = Color.Gray)
    }
}

