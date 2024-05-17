package com.jhostinluna.heroes.core.common

import retrofit2.Call
import java.io.IOException
import java.lang.RuntimeException

fun <T,R>request(call: Call<T>, transform: (T) -> R, default: T): Resource<Failure,R> {
    return try {
        val response = call.execute()
        when(response.isSuccessful) {
            true -> {
                Resource.Success(transform(response.body() ?: default))
            }
            false -> {
                Resource.Error(Failure.ServerError(response.message(),response.code()))
            }
        }
    } catch (e: IOException) {
        Resource.Error(Failure.CustomError("Error in Runtime", e.hashCode()))
    } catch (e: RuntimeException) {
        Resource.Error(Failure.CustomError("Error in Runtime", e.hashCode()))

    }
}