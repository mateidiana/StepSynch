package com.example.stepsynch.models

data class UserStatsGame(
    val id: String = "",
    val energyPoints: Int = 0,
    val rank: Int = 0,
    val totalEnergyPoints: Int = 0,
    val totalSteps: Int = 0,
    val activeChallengesCount: Int = 0,
    val earnedBadgesCount: Int = 0,
    val userUid: String = ""
)