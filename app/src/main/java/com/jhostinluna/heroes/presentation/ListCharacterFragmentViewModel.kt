package com.jhostinluna.heroes.presentation

import androidx.lifecycle.viewModelScope
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.platform.BaseViewModel
import com.jhostinluna.heroes.domain.entities.CharacterModel
import com.jhostinluna.heroes.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ListCharacterFragmentViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): BaseViewModel() {
    private val _charactersState: MutableStateFlow<List<CharacterModel>> = MutableStateFlow(
        emptyList()
    )
    val charactersState: StateFlow<List<CharacterModel>> = _charactersState

    init {

    }

    fun loadCharacters(){

        getCharactersUseCase.invoke(coroutineScope = viewModelScope, params = GetCharactersUseCase.Params()){resource->
            when(resource){
                is Resource.Success -> {
                    resource.data?.let {data->
                        _charactersState.value = data

                    }
                }
                is Resource.Error -> {
                    resource.error?.let { failure ->

                    }
                }
            }
        }
    }
}