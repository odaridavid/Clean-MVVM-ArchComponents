package com.k0d4black.theforce.domain.utils

/**
 * A generic class that represents data state
 */
sealed class ResultWrapper<out R>


class Success<out T>(val data: T) : ResultWrapper<T>()

class Error(val exception: Throwable) : ResultWrapper<Nothing>()

object Loading : ResultWrapper<Nothing>()
