package com.jhostinluna.heroes.data.repositories

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.data.network.entities.character.CharacterEntity
import com.jhostinluna.heroes.data.network.entities.comic.ComicsEntity

interface RemoteDataSourceInterface {
    fun getListCharacters(): Resource<Failure,CharacterEntity>
    fun getComicsOfCharacter(characterID: String): Resource<Failure,ComicsEntity>
}