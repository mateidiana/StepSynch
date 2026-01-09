package com.example.stepsynch.models

data class FriendRequest(
    val id: String = "",
    val user1Uid: String = "",
    val user2Uid: String = "",
    val accepted: Boolean = false
)
