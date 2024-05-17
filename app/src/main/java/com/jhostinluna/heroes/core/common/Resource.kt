package com.jhostinluna.heroes.core.common

sealed class Resource <out L, out R>{
    /**
     * Clas that represent Failure
     */
    data class Error<out L>(val l: L): Resource<L,Nothing>()
    data class Success<out R>(val r: R): Resource<Nothing,R>()

}