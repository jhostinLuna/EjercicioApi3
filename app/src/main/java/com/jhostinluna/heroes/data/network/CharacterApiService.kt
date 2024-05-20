package com.jhostinluna.heroes.data.network

import retrofit2.Call
import retrofit2.http.GET

interface CharacterApiService {
    @GET("v1/public/characters")
    fun getCharacters(): Call<WrapperResponse>
}