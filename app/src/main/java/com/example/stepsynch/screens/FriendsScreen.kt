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
import com.example.stepsynch.models.FriendRequest
import com.example.stepsynch.models.Friend

data class FriendUI(
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
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    var people by remember { mutableStateOf<List<FriendUI>>(emptyList()) }
    var selectedTab by remember { mutableStateOf("people") }
    var requests by remember { mutableStateOf<List<FriendRequest>>(emptyList()) }
    var friends by remember { mutableStateOf<List<Friend>>(emptyList()) }

    //Load users from Firestore
    LaunchedEffect(Unit) {
        authRepository.getAllUsers { users ->
            val filteredUsers = users.filter { it.uid != currentUserId }

            // For each user, fetch their stats
            val friendsList = mutableListOf<FriendUI>()
            filteredUsers.forEach { user ->
                // Fetch GF stats (steps, streak)
                authRepository.getUserStats(user.uid) { gfStats ->
                    // Fetch Game stats (energy)
                    authRepository.getUserGameStats(user.uid) { gameStats ->

                        // Add friend to list with real stats
                        val friend = FriendUI(
                            id = user.uid,
                            name = user.username,
                            initials = user.username
                                .split(" ")
                                .take(2)
                                .joinToString("") { it.first().uppercase() },
                            steps = gfStats?.stepCountToday ?: 0,
                            streak = gfStats?.streak ?: 0,
                            energy = gameStats?.energyPoints ?: 0
                        )

                        friendsList.add(friend)
                        // Update the state so UI refreshes
                        people = friendsList.toList()
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        currentUserId?.let { uid ->
            authRepository.getFriendRequests(uid) { list ->
                requests = list
            }
        }
    }

    LaunchedEffect(Unit) {
        currentUserId?.let { uid ->
            authRepository.getFriends(uid) { list ->
                friends = list
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
                                        onClick = {
                                            currentUserId?.let { sender ->
                                                authRepository.sendFriendRequest(sender, friend.id) {
                                                    // Optional: show toast/snackbar "Request sent"
                                                }
                                            }
                                        },
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

            "friends" -> {
                val friendsUI = remember(friends, people) {
                    friends.map { friend ->
                        val friendUid = if (friend.user1Uid == currentUserId) friend.user2Uid else friend.user1Uid
                        val user = people.find { it.id == friendUid }
                        FriendUI(
                            id = friend.id,
                            name = user?.name ?: "Unknown",
                            initials = user?.initials ?: "",
                            steps = user?.steps ?: 0,
                            energy = user?.energy ?: 0,
                            streak = user?.streak ?: 0
                        )
                    }
                }


                LazyColumn {
                    items(friendsUI) { friendUI ->
                        Text(friendUI.name) // now it works
                    }
                }
            }

            "requests" -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(requests) { request ->
                        Card {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(request.user1Uid) // You can fetch sender's username for nicer UI
                                Spacer(Modifier.width(12.dp))
                                Button(onClick = {
                                    authRepository.acceptFriendRequest(
                                        request.user1Uid,
                                        request.user2Uid
                                    ) {
                                        // Refresh requests list after accepting
                                        requests = requests.filter { !(it.user1Uid == request.user1Uid && it.user2Uid == request.user2Uid) }

                                        // Optionally refresh friends list
                                        currentUserId?.let { uid ->
                                            authRepository.getFriends(uid) { list -> friends = list }
                                        }
                                    }
                                }) {
                                    Text("Accept")
                                }

//                                Button(onClick = {
//                                    authRepository.acceptFriendRequest(
//                                        request.id,
//                                        request.user1Uid,
//                                        request.user2Uid
//                                    ) {
//                                        // Refresh requests list
//                                        requests = requests.filter { it.id != request.id }
//                                    }
//                                }) {
//                                    Text("Accept")
//                                }
                            }
                        }
                    }
                }
            }
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

