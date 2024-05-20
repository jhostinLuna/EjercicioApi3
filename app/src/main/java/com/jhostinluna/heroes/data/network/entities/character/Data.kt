package com.jhostinluna.heroes.data.network.entities.character


import com.google.gson.annotations.SerializedName
import com.jhostinluna.heroes.domain.entities.CharacterModel

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("total")
    val total: Int?
){
    fun toCharactersModel(): List<CharacterModel> {
        return results?.map {

            CharacterModel(
                id = it.id?:0,
                name = it.name?:"",
                imageUrl = "${it.thumbnail?.path}${it.thumbnail?.extension}",
                description = it.description?:"",
                dateModify = it.modified,
                listUriComics = it.comics?.items?.map {
                    CharacterModel.Comic(title = it?.name?:"", resourceUri = it?.resourceURI?:"")
                }?: emptyList()
            )

        }?: emptyList()
    }
}