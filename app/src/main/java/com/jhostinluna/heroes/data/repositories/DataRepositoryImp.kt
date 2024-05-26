package com.jhostinluna.heroes.data.repositories

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.platform.NetworkHandler
import com.jhostinluna.heroes.domain.models.CharacterModel
import com.jhostinluna.heroes.domain.models.ComicModel
import com.jhostinluna.heroes.domain.interfaces.DataRepositoryInterface
import javax.inject.Inject

class DataRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface,
    private val networkHandler: NetworkHandler
): DataRepositoryInterface {
    override fun getCharacters(): Resource<Failure, List<CharacterModel>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                val resource = remoteDataSource.getListCharacters()
                return when(resource.isSuccess) {
                    true -> Resource.Success(resource.data?.data?.toCharactersModel()?: emptyList())
                    false -> Resource.Error(resource.error?:Failure.CustomError(messageError = "Unknow Error on device", codeError = -1))
                }
            }
            false -> {
                Resource.Error(Failure.NetworkConnectionError(messageError = "Not Network Connection", codeError = -11))
            }
        }


    }

    override fun getComicsOfCharacter(characterID: String): Resource<Failure, List<ComicModel>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> {
                val resource = remoteDataSource.getComicsOfCharacter(characterID)
                when(resource.isSuccess) {
                    true -> Resource.Success(resource.data?.data?.results?.map { it.toComicModel() }?: emptyList())
                    false -> Resource.Error(resource.error?:Failure.CustomError(messageError = "Unknow Error on device", codeError = -1))
                }
            }
            false -> {
                Resource.Error(Failure.NetworkConnectionError(messageError = "Not Network Connection", codeError = -11))
            }
        }
    }


}