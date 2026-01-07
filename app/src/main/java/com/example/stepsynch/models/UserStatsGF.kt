package com.example.stepsynch.models

data class UserStatsGF(
    val id: String = "",
    val stepCountToday: Int = 0,
    val dailyStepGoal: Int = 0,
    val streak: Int = 0,
    val caloriesToday: Int = 0,
    val distanceToday: Double = 0.0,
    val weeklyAverage: Int = 0,
    val stepCountThisWeek: Int = 0,
    val userUid: String = ""
)