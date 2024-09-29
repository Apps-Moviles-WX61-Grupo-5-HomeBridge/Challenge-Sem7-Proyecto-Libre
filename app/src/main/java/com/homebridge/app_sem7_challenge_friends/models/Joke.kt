package com.homebridge.app_sem7_challenge_friends.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke (
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "setup")
    val setup: String?,
    @ColumnInfo(name = "delivery")
    val delivery: String?,
    @ColumnInfo(name = "joke")
    val joke: String?
)