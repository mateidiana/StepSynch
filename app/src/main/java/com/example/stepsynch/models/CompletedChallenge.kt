package com.example.stepsynch.models

data class CompletedChallenge(
    val challengeId: Int = 0,
    val userUid: String = "",
    val completedAt: Long = System.currentTimeMillis()
)