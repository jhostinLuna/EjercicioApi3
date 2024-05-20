package com.jhostinluna.heroes.domain.interfaces

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.domain.entities.CharacterModel

interface DataRepositoryInterface {
    fun getCharacters():Resource<Failure,List<CharacterModel>>
}