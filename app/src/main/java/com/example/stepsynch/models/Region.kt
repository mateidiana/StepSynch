package com.example.stepsynch.models

enum class RegionType { CITY, NATURE }
enum class RegionStatus { LOCKED, UNLOCKED, EXPLORING, COMPLETED }

data class Region (
    val id: Int,
    val name: String,
    val energyCost: Int,
    val stepsRequired: Int,
    val type: RegionType,
    val status: RegionStatus,
    val progress: Int,
    val x: Float,
    val y: Float
)

