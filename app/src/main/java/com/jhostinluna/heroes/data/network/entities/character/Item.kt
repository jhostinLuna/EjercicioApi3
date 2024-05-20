package com.jhostinluna.heroes.data.network.entities.character


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("name")
    val name: String?,
    @SerializedName("resourceURI")
    val resourceURI: String?
)