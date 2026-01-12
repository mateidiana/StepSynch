package com.example.stepsynch.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.stepsynch.models.User
import com.example.stepsynch.models.UserStatsGF
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.repository.AuthRepository

fun getInitials(username: String): String {
    return username
        .trim()
        .split(" ")
        .take(2)
        .joinToString("") { it.first().uppercase() }
}

@Composable
fun LeaderboardScreen(
    navController: NavController,
    authRepository: AuthRepository
) {
    var selectedTab by remember { mutableStateOf("steps") }

    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var statsGF by remember { mutableStateOf<List<UserStatsGF>>(emptyList()) }
    var statsGame by remember { mutableStateOf<List<UserStatsGame>>(emptyList()) }
    val currentUserUid = authRepository.currentUser.collectAsState().value?.uid

    // 🔹 Load all data (same pattern as FriendsScreen)
    LaunchedEffect(Unit) {
        authRepository.getAllUsers { fetchedUsers ->
            users = fetchedUsers
        }

        authRepository.getAllUserStatsGF { fetchedStats ->
            statsGF = fetchedStats
        }

        authRepository.getAllUserStatsGame { fetchedStats ->
            statsGame = fetchedStats
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF8FAF6),
                        Color(0xFF95B46A).copy(alpha = 0.1f)
                    )
                )
            )
    ) {

        // --- Header ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp, end = 16.dp, bottom = 16.dp, top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    "Leaderboard",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF172815)
                )
                Text(
                    "See how you rank among others",
                    fontSize = 14.sp,
                    color = Color(0xFF3E5622).copy(alpha = 0.7f)
                )
            }
        }

        // --- Tabs ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            TabButtonPage("Steps Today", selectedTab == "steps") {
                selectedTab = "steps"
            }
            TabButtonPage("Energy Today", selectedTab == "energy") {
                selectedTab = "energy"
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- Leaderboard List ---
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (selectedTab == "steps") {
                val sorted = statsGF.sortedByDescending { it.stepCountToday }

                itemsIndexed(sorted) { index, stat ->
                    val user = users.find { it.uid == stat.userUid } ?: return@itemsIndexed

                    LeaderboardCard(
                        position = index + 1,
                        initials = getInitials(user.username),
                        name = user.username,
                        value = stat.stepCountToday,
                        label = "steps",
                        isCurrentUser = user.uid == currentUserUid
                    )
                }
            } else {
                val sorted = statsGame.sortedByDescending { it.energyPoints }

                itemsIndexed(sorted) { index, stat ->
                    val user = users.find { it.uid == stat.userUid } ?: return@itemsIndexed

                    LeaderboardCard(
                        position = index + 1,
                        initials = getInitials(user.username),
                        name = user.username,
                        value = stat.energyPoints,
                        label = "energy",
                        isCurrentUser = user.uid == currentUserUid
                    )
                }
            }
        }
    }
}

@Composable
fun LeaderboardCard(
    position: Int,
    initials: String,
    name: String,
    value: Int,
    label: String,
    isCurrentUser: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "#$position",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier.width(40.dp)
                //modifier = Modifier.width(32.dp)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF95B46A)),
                contentAlignment = Alignment.Center
            ) {
                Text(initials, color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                //Text(name, fontWeight = FontWeight.Bold, color = Color(0xFF172815))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(name, fontWeight = FontWeight.Bold, color = Color(0xFF172815))

                    if (isCurrentUser) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "You",
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .background(Color(0xFF3E5622), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    "$value $label",
                    color = Color(0xFF3E5622).copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
fun TabButtonPage(text: String, selected: Boolean, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected)
                Color(0xFF3E5622)
            else
                Color(0xFF3E5622).copy(alpha = 0.6f)
        )
    }
}

