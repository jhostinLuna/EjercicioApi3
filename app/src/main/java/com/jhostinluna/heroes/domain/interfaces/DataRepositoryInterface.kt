package com.jhostinluna.heroes.domain.interfaces

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.domain.models.CharacterModel
import com.jhostinluna.heroes.domain.models.ComicModel

interface DataRepositoryInterface {
    fun getCharacters():Resource<Failure,List<CharacterModel>>
    fun getComicsOfCharacter(characterID: String): Resource<Failure,List<ComicModel>>
}