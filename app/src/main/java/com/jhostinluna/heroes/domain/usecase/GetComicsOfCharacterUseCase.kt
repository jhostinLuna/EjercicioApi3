package com.jhostinluna.heroes.domain.usecase

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.interactor.UseCase
import com.jhostinluna.heroes.domain.models.ComicModel
import com.jhostinluna.heroes.domain.interfaces.DataRepositoryInterface
import javax.inject.Inject

class GetComicsOfCharacterUseCase @Inject constructor(
    private val dataRepository: DataRepositoryInterface
): UseCase<GetComicsOfCharacterUseCase.Params, List<ComicModel>>() {
    class Params(
        val uri: String
    )

    override fun execute(parameters: Params): Resource<Failure, List<ComicModel>> = dataRepository.getComicsOfCharacter(parameters.uri)
}
