package com.jhostinluna.heroes.domain.entities

import java.util.Date

data class CharacterModel(
    val id: Int = 0,
    val name: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val dateModify: String? = null,
    val listUriComics: List<Comic> = emptyList()

)
{
    class Comic(
        val title: String,
        val resourceUri: String
    )
}