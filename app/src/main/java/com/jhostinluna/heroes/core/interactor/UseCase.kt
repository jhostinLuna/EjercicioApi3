package com.jhostinluna.heroes.core.interactor

import com.jhostinluna.heroes.core.common.Failure
import com.jhostinluna.heroes.core.common.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<in P, out R> where R : Any {
    operator fun invoke(coroutineScope: CoroutineScope,params: P, onResult: (Resource<Failure, R>) -> Unit = {}) {
        coroutineScope.launch(Dispatchers.Main) {
            val job = async(Dispatchers.IO) { execute(params) }
            onResult(job.await())
        }
    }

    abstract fun execute(parameters: P): Resource<Failure, R>
}