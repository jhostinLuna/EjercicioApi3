package com.jhostinluna.heroes.data.repositories

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.domain.entities.CharacterModel
import com.jhostinluna.heroes.domain.interfaces.DataRepositoryInterface
import javax.inject.Inject

class DataRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface
): DataRepositoryInterface {
    override fun getCharacters(): Resource<Failure, List<CharacterModel>> {
        val resource = remoteDataSource.getListCharacters()
        return when(resource.isSuccess) {
            true -> Resource.Success(resource.data?.data?.toCharactersModel()?: emptyList())
            false -> Resource.Error(resource.error?:Failure.CustomError(messageError = "Unknow Error on device", codeError = -1))
        }

    }
}