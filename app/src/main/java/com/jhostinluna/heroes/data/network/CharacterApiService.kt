package com.jhostinluna.heroes.data.network

import com.jhostinluna.heroes.data.network.entities.character.CharacterEntity
import com.jhostinluna.heroes.data.network.entities.comic.ComicsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") ts:Long,
        @Query("apikey") apikey:String,
        @Query("hash") hash: String
    ): Call<CharacterEntity>
    @GET("v1/public/characters/{id}/comics")
    fun getComicsOfCharacter(
        @Path("id") characterID: String,
        @Query("ts") ts:Long,
        @Query("apikey") apikey:String,
        @Query("hash") hash: String
    ): Call<ComicsEntity>
}