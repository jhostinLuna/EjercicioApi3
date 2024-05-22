package com.jhostinluna.heroes.data.network.entities.comic


import com.google.gson.annotations.SerializedName

data class ComicsEntity(
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("data")
    val data: Data?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("status")
    val status: String?
){
    companion object {
        fun empty() = ComicsEntity(
            attributionHTML = "",
            attributionText = "",
            code = 0,
            copyright = "",
            data = null,
            etag = "",
            status = ""
        )
    }

}