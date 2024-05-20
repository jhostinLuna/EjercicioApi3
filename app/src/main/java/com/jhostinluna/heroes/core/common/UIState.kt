package com.jhostinluna.heroes.core.common

sealed class UIState<out T> {
    data class Success<out T>(val data: T): UIState<T>()
    data class Error(val failure: Failure): UIState<Nothing>()
    object Loading: UIState<Nothing>()
}