package com.example.stepsynch.repository

import com.example.stepsynch.models.User
import com.example.stepsynch.models.UserStatsGF
import com.example.stepsynch.models.UserStatsGame
import com.example.stepsynch.models.AddedLandmark
import com.example.stepsynch.models.CompletedRegion
import com.example.stepsynch.models.Friend
import com.example.stepsynch.models.FriendRequest
import com.example.stepsynch.models.ActiveChallenge
import com.example.stepsynch.models.CompletedChallenge
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _currentUser = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val currentUser = _currentUser.asStateFlow()

    init {
        auth.addAuthStateListener { firebaseAuth ->
            _currentUser.value = firebaseAuth.currentUser
        }
    }

    fun signUp(
        email: String,
        password: String,
        username: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val firebaseUser = auth.currentUser
                    _currentUser.value = firebaseUser

                    // Immediately notify UI to continue (navigation, stop loading, etc.)
                    onResult(true, null)

                    // Prepare Firestore user data
                    val user = User(
                        uid = firebaseUser?.uid ?: "",
                        email = email,
                        username = username
                    )

                    firebaseUser?.uid?.let { uid ->
                        firestore.collection("users")
                            .document(uid)
                            .set(user)
                            .addOnFailureListener { e ->
                                // Optional: log error
                                println("Firestore user create FAILED: ${e.message}")
                            }
                        val stats = UserStatsGF(
                            id = uid,
                            stepCountToday = 8500,
                            dailyStepGoal = 10000,
                            streak = 5,
                            caloriesToday = 230,
                            distanceToday = 5.5,
                            weeklyAverage = 9130,
                            stepCountThisWeek = 30000,
                            userUid = uid
                        )

                        firestore.collection("user_stats_gf")
                            .document(uid)
                            .set(stats)
                            .addOnFailureListener { e ->
                                println("Stats create FAILED: ${e.message}")
                            }

                        getUserCount { userCount ->
                            val gameStats = UserStatsGame(
                                id = uid,
                                energyPoints = 5000,
                                rank = userCount,
                                totalEnergyPoints = 5000,
                                totalSteps = 8500,
                                activeChallengesCount = 0,
                                earnedBadgesCount = 0,
                                userUid = uid
                            )

                            firestore.collection("user_stats_game")
                                .document(uid)
                                .set(gameStats)
                                .addOnFailureListener { e ->
                                    println("Game stats create FAILED: ${e.message}")
                                }
                        }
                        // 🔹 Added Landmark (initial)
                        val addedLandmark = AddedLandmark(
                            regionId = 1,
                            landmarkId = 1,
                            userUid = uid
                        )

                        firestore.collection("added_landmark")
                            .add(addedLandmark)

                        // 🔹 Completed Region (initial)
                        val completedRegion = CompletedRegion(
                            regionId = 1,
                            userUid = uid
                        )

                        firestore.collection("completed_region")
                            .add(completedRegion)

                    }

                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }


    // Login with email & password
    fun login(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //_currentUser.value = auth.currentUser
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    fun logout() {
        auth.signOut()
        _currentUser.value = null
    }

    // Optional: fetch current user info from Firestore
    fun getUser(uid: String, onResult: (User?) -> Unit) {
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { snapshot ->
                val user = snapshot.toObject(User::class.java)
                onResult(user)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun getAllUsers(onResult: (List<User>) -> Unit) {
        firestore.collection("users")
            .get()
            .addOnSuccessListener { snapshot ->
                val users = snapshot.toObjects(User::class.java)
                onResult(users)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    private fun getUserCount(onResult: (Int) -> Unit) {
        firestore.collection("users")
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.size())
            }
            .addOnFailureListener {
                onResult(0)
            }
    }

    fun getUserStats(
        uid: String,
        onResult: (UserStatsGF?) -> Unit
    ) {
        firestore.collection("user_stats_gf")
            .document(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObject(UserStatsGF::class.java))
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun getUserGameStats(
        uid: String,
        onResult: (UserStatsGame?) -> Unit
    ) {
        firestore.collection("user_stats_game")
            .document(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObject(UserStatsGame::class.java))
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun getAllUserStatsGF(onResult: (List<UserStatsGF>) -> Unit) {
        firestore.collection("user_stats_gf")
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObjects(UserStatsGF::class.java))
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun getAllUserStatsGame(onResult: (List<UserStatsGame>) -> Unit) {
        firestore.collection("user_stats_game")
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.toObjects(UserStatsGame::class.java))
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun getCompletedRegionCount(uid: String, onResult: (Int) -> Unit) {
        firestore.collection("completed_region")
            .whereEqualTo("userUid", uid)
            .get()
            .addOnSuccessListener { snapshot ->
                onResult(snapshot.size())
            }
            .addOnFailureListener {
                onResult(0)
            }
    }

    fun addLandmarkForUser(
        regionId: Int,
        landmarkId: Int,
        userUid: String,
        onComplete: () -> Unit = {}
    ) {
        val doc = firestore.collection("added_landmark").document()
        val data = mapOf(
            "regionId" to regionId,
            "landmarkId" to landmarkId,
            "userUid" to userUid
        )
        doc.set(data).addOnSuccessListener { onComplete() }
    }

    fun getCollectedLandmarks(
        regionId: Int,
        userUid: String,
        onResult: (List<Int>) -> Unit
    ) {
        firestore.collection("added_landmark")
            .whereEqualTo("regionId", regionId)
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.documents.mapNotNull { it.getLong("landmarkId")?.toInt() })
            }
    }

    fun markRegionCompleted(
        regionId: Int,
        userUid: String,
        onComplete: () -> Unit = {}
    ) {
        val doc = firestore.collection("completed_region").document()
        doc.set(
            mapOf(
                "regionId" to regionId,
                "userUid" to userUid
            )
        ).addOnSuccessListener { onComplete() }
    }

    fun isRegionCompleted(
        regionId: Int,
        userUid: String,
        onResult: (Boolean) -> Unit
    ) {
        firestore.collection("completed_region")
            .whereEqualTo("regionId", regionId)
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { onResult(!it.isEmpty) }
    }

    fun updateEnergy(
        userUid: String,
        deltaEnergy: Int,
        deltaTotalEnergy: Int = 0
    ) {
        firestore.collection("user_stats_game")
            .whereEqualTo("userUid", userUid)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnap ->
                val doc = querySnap.documents.firstOrNull() ?: return@addOnSuccessListener
                val docRef = doc.reference

                firestore.runTransaction { tx ->
                    val snap = tx.get(docRef)

                    val currentEnergy = snap.getLong("energyPoints") ?: 0
                    val totalEnergy = snap.getLong("totalEnergyPoints") ?: 0

                    tx.update(
                        docRef,
                        mapOf(
                            "energyPoints" to (currentEnergy + deltaEnergy),
                            "totalEnergyPoints" to (totalEnergy + deltaTotalEnergy)
                        )
                    )
                }
            }
    }

    // Send friend request
    fun sendFriendRequest(senderUid: String, receiverUid: String, onComplete: () -> Unit = {}) {
        val doc = firestore.collection("friend_request").document()
        val data = mapOf(
            "user1Uid" to senderUid,
            "user2Uid" to receiverUid,
            "accepted" to false
        )
        doc.set(data).addOnSuccessListener { onComplete() }
    }

    // Get friend requests for current user
    fun getFriendRequests(userUid: String, onResult: (List<FriendRequest>) -> Unit) {
        firestore.collection("friend_request")
            .whereEqualTo("user2Uid", userUid)
            .whereEqualTo("accepted", false)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.toObjects(FriendRequest::class.java))
            }
    }

    fun acceptFriendRequest(user1Uid: String, user2Uid: String, onComplete: () -> Unit = {}) {
        val friendDoc = firestore.collection("friend").document()
        val data = mapOf(
            "user1Uid" to user1Uid,
            "user2Uid" to user2Uid
        )

        // Create the friend entry
        friendDoc.set(data)
            .addOnSuccessListener {
                // Find the friend request document by the two user IDs
                firestore.collection("friend_request")
                    .whereEqualTo("user1Uid", user1Uid)
                    .whereEqualTo("user2Uid", user2Uid)
                    .get()
                    .addOnSuccessListener { snap ->
                        if (snap.documents.isNotEmpty()) {
                            val requestDoc = snap.documents.first()
                            requestDoc.reference.update("accepted", true)
                                .addOnSuccessListener { onComplete() }
                                .addOnFailureListener { e ->
                                    println("Failed to update friend request: ${e.message}")
                                }
                        } else {
                            println("Friend request not found")
                        }
                    }
                    .addOnFailureListener { e ->
                        println("Failed to query friend request: ${e.message}")
                    }
            }
            .addOnFailureListener { e ->
                println("Failed to create friend: ${e.message}")
            }
    }

    fun getFriends(userUid: String, onResult: (List<Friend>) -> Unit) {
        firestore.collection("friend")
            .whereIn("user1Uid", listOf(userUid))
            .get()
            .addOnSuccessListener { snap1 ->
                firestore.collection("friend")
                    .whereIn("user2Uid", listOf(userUid))
                    .get()
                    .addOnSuccessListener { snap2 ->
                        val combined = snap1.toObjects(Friend::class.java) + snap2.toObjects(Friend::class.java)
                        onResult(combined)
                    }
            }
    }

    fun joinTeam(userUid: String, teamId: Int, onComplete: () -> Unit = {}) {
        firestore.collection("belongs_to_team")
            .add(
                mapOf(
                    "userUid" to userUid,
                    "teamId" to teamId
                )
            )
            .addOnSuccessListener { onComplete() }
    }

    fun getTeamMembers(teamId: Int, onResult: (List<String>) -> Unit) {
        firestore.collection("belongs_to_team")
            .whereEqualTo("teamId", teamId)
            .get()
            .addOnSuccessListener { snap ->
                val userIds = snap.documents.mapNotNull { it.getString("userUid") }
                onResult(userIds)
            }
    }

    fun getTeamTotalEnergy(teamId: Int, onResult: (Int) -> Unit) {
        getTeamMembers(teamId) { members ->
            if (members.isEmpty()) {
                onResult(0)
                return@getTeamMembers
            }

            firestore.collection("user_stats_game")
                .whereIn("userUid", members)
                .get()
                .addOnSuccessListener { snap ->
                    val totalEnergy = snap.documents.sumOf {
                        it.getLong("energyPoints")?.toInt() ?: 0
                    }
                    onResult(totalEnergy)
                }
                .addOnFailureListener {
                    onResult(0)
                }
        }
    }

    fun getUserTeam(
        userUid: String,
        onResult: (Int?) -> Unit
    ) {
        firestore.collection("belongs_to_team")
            .whereEqualTo("userUid", userUid)
            .limit(1)
            .get()
            .addOnSuccessListener { snap ->
                val teamId = snap.documents.firstOrNull()?.getLong("teamId")?.toInt()
                onResult(teamId)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun getActiveChallenges(
        userUid: String,
        onResult: (List<Int>) -> Unit
    ) {
        firestore.collection("active_challenge")
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { snap ->
                val ids = snap.documents.mapNotNull {
                    it.getLong("challengeId")?.toInt()
                }
                onResult(ids)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun getCompletedChallenges(
        userUid: String,
        onResult: (List<Int>) -> Unit
    ) {
        firestore.collection("completed_challenge")
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { snap ->
                val ids = snap.documents.mapNotNull {
                    it.getLong("challengeId")?.toInt()
                }
                onResult(ids)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }

    fun addActiveChallenge(
        userUid: String,
        challengeId: Int,
        onComplete: () -> Unit = {}
    ) {
        val entry = ActiveChallenge(
            challengeId = challengeId,
            userUid = userUid
        )

        firestore.collection("active_challenge")
            .add(entry)
            .addOnSuccessListener { onComplete() }
    }

    fun completeChallenge(
        userUid: String,
        challengeId: Int,
        onComplete: () -> Unit = {}
    ) {
        val completedEntry = CompletedChallenge(
            challengeId = challengeId,
            userUid = userUid
        )

        // 1️⃣ Add to completed_challenge
        firestore.collection("completed_challenge")
            .add(completedEntry)
            .addOnSuccessListener {

                // 2️⃣ Remove from active_challenge
                firestore.collection("active_challenge")
                    .whereEqualTo("userUid", userUid)
                    .whereEqualTo("challengeId", challengeId)
                    .get()
                    .addOnSuccessListener { snap ->
                        snap.documents.forEach { it.reference.delete() }
                        onComplete()
                    }
            }
    }

    fun getActiveChallengeCount(
        userUid: String,
        onResult: (Int) -> Unit
    ) {
        firestore.collection("active_challenge")
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.size())
            }
            .addOnFailureListener {
                onResult(0)
            }
    }

    fun getCompletedChallengeCount(
        userUid: String,
        onResult: (Int) -> Unit
    ) {
        firestore.collection("completed_challenge")
            .whereEqualTo("userUid", userUid)
            .get()
            .addOnSuccessListener { snap ->
                onResult(snap.size())
            }
            .addOnFailureListener {
                onResult(0)
            }
    }

    fun ensureUserStats(uid: String) {
        val statsRef = firestore.collection("user_stats_gf").document(uid)

        statsRef.get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {

                val stats = UserStatsGF(
                    id = uid,
                    stepCountToday = 8500,
                    dailyStepGoal = 10000,
                    streak = 5,
                    caloriesToday = 230,
                    distanceToday = 5.5,
                    weeklyAverage = 9130,
                    stepCountThisWeek = 30000,
                    userUid = uid
                )

                statsRef.set(stats)
            }
        }
    }

    fun ensureUserStatsGame(uid: String) {
        val docRef = firestore.collection("user_stats_game").document(uid)

        docRef.get()
            .addOnSuccessListener { snapshot ->
                if (!snapshot.exists()) {
                    getUserCount { userCount ->
                        val stats = UserStatsGame(
                            id = uid,
                            energyPoints = 1000,
                            rank = userCount + 1,
                            totalEnergyPoints = 1000,
                            totalSteps = 8500,
                            activeChallengesCount = 0,
                            earnedBadgesCount = 0,
                            userUid = uid
                        )

                        docRef.set(stats)
                    }
                }
            }
    }

    fun ensureAddedLandmark(uid: String) {
        firestore.collection("added_landmark")
            .whereEqualTo("userUid", uid)
            .whereEqualTo("regionId", 1)
            .whereEqualTo("landmarkId", 1)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    val entry = AddedLandmark(
                        regionId = 1,
                        landmarkId = 1,
                        userUid = uid
                    )

                    firestore.collection("added_landmark").add(entry)
                }
            }
    }

    fun ensureCompletedRegion(uid: String) {
        firestore.collection("completed_region")
            .whereEqualTo("userUid", uid)
            .whereEqualTo("regionId", 1)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.isEmpty) {
                    val entry = CompletedRegion(
                        regionId = 1,
                        userUid = uid
                    )

                    firestore.collection("completed_region").add(entry)
                }
            }
    }

}

