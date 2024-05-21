package com.jhostinluna.heroes.presentation


import com.jhostinluna.heroes.core.common.UIState
import com.jhostinluna.heroes.core.platform.BaseViewModel
import com.jhostinluna.heroes.domain.models.ComicModel
import com.jhostinluna.heroes.domain.usecase.GetComicsOfCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val getComicsOfCharacterUseCase: GetComicsOfCharacterUseCase
): BaseViewModel() {
    private val _comicsOfCharacterUIState = MutableStateFlow<UIState<List<ComicModel>>>(UIState.Loading)
    val comicsOfCharacterUIState: StateFlow<UIState<List<ComicModel>>> = _comicsOfCharacterUIState


}