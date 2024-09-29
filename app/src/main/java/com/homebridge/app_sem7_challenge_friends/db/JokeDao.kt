package com.homebridge.app_sem7_challenge_friends.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.homebridge.app_sem7_challenge_friends.models.Joke

@Dao
interface JokeDao {
    @Insert
    fun insertOne(joke: Joke)

    @Query("SELECT * FROM joke")
    fun getAll(): List<Joke>

    @Delete
    fun delete(joke: Joke)
}