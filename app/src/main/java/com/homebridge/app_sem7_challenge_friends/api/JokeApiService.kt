package com.homebridge.app_sem7_challenge_friends.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokeApiService {
    private const val BASE_URL = "https://v2.jokeapi.dev/joke/Any"

    val api: JokeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokeApi::class.java)
    }
}
