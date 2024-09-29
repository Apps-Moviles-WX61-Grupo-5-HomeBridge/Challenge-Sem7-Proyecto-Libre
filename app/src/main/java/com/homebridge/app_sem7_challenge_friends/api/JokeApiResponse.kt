package com.homebridge.app_sem7_challenge_friends.api

import com.homebridge.app_sem7_challenge_friends.models.Joke

class JokeApiResponse(
    private var category: String,
    private var setup: String,
    private var delivery: String,
) {
    fun toJoke() : Joke {
        return Joke(
            category = category,
            setup =  setup,
            delivery = delivery)
    }
}