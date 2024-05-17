package com.jhostinluna.heroes.data.network

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.common.request
import com.jhostinluna.heroes.data.network.character.WrapperResponse
import com.jhostinluna.heroes.data.repositories.RemoteDataSourceInterface
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: CharacterApiService
): RemoteDataSourceInterface {
    override fun getListCharacters(): Resource<Failure,WrapperResponse> {
        request(
            apiService.getCharacters(),
            { it },
            WrapperResponse()
        )
    }
}