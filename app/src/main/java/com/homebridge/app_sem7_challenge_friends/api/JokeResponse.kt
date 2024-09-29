package com.homebridge.app_sem7_challenge_friends.api

data class JokeResponse(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String
)
