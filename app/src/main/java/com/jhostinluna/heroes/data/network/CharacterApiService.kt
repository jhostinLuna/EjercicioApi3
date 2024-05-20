package com.jhostinluna.heroes.data.network

import com.jhostinluna.heroes.data.network.entities.character.CharacterEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") ts:Int,
        @Query("apikey") apikey: String = "e87cbdf52f69bbb368311eebcee6bb96",
        @Query("hash") hash: String = "08954c545f07f758d9df46f9e027c24a"
    ): Call<CharacterEntity>
}