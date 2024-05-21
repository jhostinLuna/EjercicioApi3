package com.jhostinluna.heroes.domain.usecase

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.interactor.UseCase
import com.jhostinluna.heroes.domain.models.CharacterModel
import com.jhostinluna.heroes.domain.interfaces.DataRepositoryInterface
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val dataRepositoryInterface: DataRepositoryInterface
): UseCase<GetCharactersUseCase.Params, List<CharacterModel>>() {
    class Params

    override fun execute(parameters: Params): Resource<Failure, List<CharacterModel>> = dataRepositoryInterface.getCharacters()

}