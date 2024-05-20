package com.jhostinluna.heroes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.core.platform.BaseViewModel
import com.jhostinluna.heroes.domain.entities.CharacterModel
import com.jhostinluna.heroes.domain.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.http.Tag
import javax.inject.Inject

@HiltViewModel
class ListCharacterFragmentViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {
    private val _charactersState = MutableStateFlow<UIState<List<CharacterModel>>>(UIState.Loading)
    val charactersState: StateFlow<UIState<List<CharacterModel>>> = _charactersState

    init {
        loadCharacters()
    }

    private fun loadCharacters(){

        getCharactersUseCase.invoke(coroutineScope = viewModelScope, params = GetCharactersUseCase.Params()){resource->
            when(resource){
                is Resource.Success -> {
                    resource.data?.let {data->
                        _charactersState.value =UIState.Success(data = data)
                        Log.d(this.javaClass.simpleName,"List SIze of:  ${data.size.toString()}")
                    }
                }
                is Resource.Error -> {
                    resource.error?.let { failure ->
                        _charactersState.value = UIState.Error(failure)
                        Log.d(this.javaClass.simpleName,"List SIze of:  ${failure}")

                    }
                }
            }
        }
    }
}