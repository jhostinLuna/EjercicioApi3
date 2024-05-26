package com.jhostinluna.heroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.domain.models.CharacterModel
import com.jhostinluna.heroes.domain.models.ComicModel
import com.jhostinluna.heroes.domain.usecase.GetCharactersUseCase
import com.jhostinluna.heroes.domain.usecase.GetComicsOfCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListCharacterFragmentViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getComicsOfCharacterUseCase: GetComicsOfCharacterUseCase
): ViewModel() {
    private val _charactersState = MutableStateFlow<UIState<List<CharacterModel>>>(UIState.Loading)
    val charactersState: StateFlow<UIState<List<CharacterModel>>> = _charactersState

    init {
        loadCharacters()
    }

    fun loadCharacters(){

        getCharactersUseCase.invoke(coroutineScope = viewModelScope, params = GetCharactersUseCase.Params()){resource->
            when(resource){
                is Resource.Success -> {
                    resource.data?.let {data->
                        _charactersState.value =UIState.Success(data = data)
                    }
                }
                is Resource.Error -> {
                    resource.error?.let { failure ->
                        _charactersState.value = UIState.Error(failure)

                    }
                }
            }
        }
    }
    private val _comicsOfCharacterUIState = MutableStateFlow<UIState<List<ComicModel>>>(UIState.Loading)
    val comicsOfCharacterUIState: StateFlow<UIState<List<ComicModel>>> = _comicsOfCharacterUIState

    fun loadComicsOfCharacter(characterID: String) {
        getComicsOfCharacterUseCase.invoke(
            coroutineScope = viewModelScope,
            params = GetComicsOfCharacterUseCase.Params(characterID = characterID)
        ) {resource ->
            when(resource) {
                is Resource.Success -> {
                    resource.data?.let { comics ->
                        _comicsOfCharacterUIState.value = UIState.Success(comics)
                    }
                }
                is Resource.Error -> {
                    resource.error?.let {failure->
                        _comicsOfCharacterUIState.value = UIState.Error(failure)
                    }
                }
            }
        }
    }
}