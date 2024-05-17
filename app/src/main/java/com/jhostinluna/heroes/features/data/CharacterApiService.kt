package com.jhostinluna.heroes.features.data

import retrofit2.http.GET

interface CharacterApiService {
    @GET("v1/public/characters")
    fun getCharacters()
}