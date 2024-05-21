package com.jhostinluna.heroes.data.network

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.common.request
import com.jhostinluna.heroes.data.network.entities.character.CharacterEntity
import com.jhostinluna.heroes.data.network.entities.comic.ComicsEntity
import com.jhostinluna.heroes.data.repositories.RemoteDataSourceInterface
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: CharacterApiService
): RemoteDataSourceInterface {
    override fun getListCharacters(): Resource<Failure,CharacterEntity> {
        return request(
            apiService.getCharacters(ts = 1),
            { it },
            CharacterEntity.empty()
        )
    }

    override fun getComicsOfCharacter(uri: String): Resource<Failure, ComicsEntity> {
        return request(
            apiService.getComicsOfCharacter(uri = uri, ts = 1),
            {
            it
            },
            ComicsEntity.empty()
        )
    }
}