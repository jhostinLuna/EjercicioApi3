package com.jhostinluna.heroes.data.network.entities.comic


import com.google.gson.annotations.SerializedName

data class TextObject(
    @SerializedName("language")
    val language: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("type")
    val type: String?
)