package com.jhostinluna.heroes.core.common

sealed class Failure {
    class NetworkConnectionError(val messageError:String, codeError: Int): Failure()
    class ServerError(val messageError: String, codeError: Int): Failure()
    class CustomError(val messageError:String, codeError: Int): Failure()
}