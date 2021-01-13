package com.k0d4black.theforce.shared.model

sealed class Result<out Type> {

    data class Success<out Type>(
        val data: Type,
        val statusMessage: String? = null,
        val statusCode: Int = -1
    ) : Result<Type>()

    data class Error(val throwable: Throwable) : Result<Nothing>()

    object Loading : Result<Nothing>()
}