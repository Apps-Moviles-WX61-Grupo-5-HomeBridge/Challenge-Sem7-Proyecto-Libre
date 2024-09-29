package com.homebridge.app_sem7_challenge_friends.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.homebridge.app_sem7_challenge_friends.models.Joke

@Database(entities = [Joke::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): JokeDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "jokesv2.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}