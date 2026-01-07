package com.example.stepsynch.repository

import com.example.stepsynch.models.User
import com.example.stepsynch.models.UserStatsGF
import com.example.stepsynch.models.UserStatsGame
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
                                energyPoints = 1000,
                                rank = userCount,
                                totalEnergyPoints = 1000,
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
}

