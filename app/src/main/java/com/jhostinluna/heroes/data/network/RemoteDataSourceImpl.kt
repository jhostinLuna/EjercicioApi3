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
        val securityAuthentication = SecurityAuthentication()
        return request(
            apiService.getCharacters(
                ts = securityAuthentication.ts?:1,
                apikey = securityAuthentication.publicKey,
                hash = securityAuthentication.getHashValue()
            ),
            { it },
            CharacterEntity.empty()
        )
    }

    override fun getComicsOfCharacter(characterID: String): Resource<Failure, ComicsEntity> {
        val securityAuthentication = SecurityAuthentication()
        return request(
            apiService.getComicsOfCharacter(
                characterID = characterID,
                ts = securityAuthentication.ts?:1,
                apikey = securityAuthentication.publicKey,
                hash = securityAuthentication.getHashValue()
            ),
            {
            it
            },
            ComicsEntity.empty()
        )
    }
}