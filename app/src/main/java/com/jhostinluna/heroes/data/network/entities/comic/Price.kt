package com.jhostinluna.heroes.data.network.entities.comic


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Double?,
    @SerializedName("type")
    val type: String?
)