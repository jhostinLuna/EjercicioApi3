package com.jhostinluna.heroes.core.common

sealed class Resource <out L, out R>(val error: L? = null,val data: R? = null){
    /**
     * Clas that represent Failure
     */
    class Error<L>(error: L): Resource<L,Nothing>(error = error)
    class Success<R>(data: R): Resource<Nothing,R>(data = data)
    val isError get() = this is Error<L>
    val isSuccess get() = this is Success<R>

}