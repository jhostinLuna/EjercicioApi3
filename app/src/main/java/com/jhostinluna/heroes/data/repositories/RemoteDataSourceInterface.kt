package com.jhostinluna.heroes.data.repositories

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import com.jhostinluna.heroes.data.network.character.WrapperResponse
import retrofit2.Call

interface RemoteDataSourceInterface {
    fun getListCharacters(): Resource<Failure,WrapperResponse>
}