package com.homebridge.app_sem7_challenge_friends.api
import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {
    @GET("joke/Any")
    fun getJoke(): Call<JokeResponse>
}