package com.example.stepsynch.repository

import com.example.stepsynch.models.User
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
}

