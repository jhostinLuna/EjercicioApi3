package com.jhostinluna.heroes.data.repositories

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.domain.models.CharacterModel
import com.jhostinluna.heroes.domain.models.ComicModel
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

    override fun getComicsOfCharacter(uri: String): Resource<Failure, List<ComicModel>> {
        val resource = remoteDataSource.getComicsOfCharacter(uri)
        return when(resource.isSuccess) {
            true -> Resource.Success(resource.data?.data?.results?.map { it.toComicModel() }?: emptyList())
            false -> Resource.Error(resource.error?:Failure.CustomError(messageError = "Unknow Error on device", codeError = -1))
        }
    }


}