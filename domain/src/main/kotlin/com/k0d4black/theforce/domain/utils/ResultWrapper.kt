package com.k0d4black.theforce.domain.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultWrapper<out R>


class Success<out T>(val data: T) : ResultWrapper<T>()

class Error(val exception: Exception) : ResultWrapper<Nothing>()

object Loading : ResultWrapper<Nothing>()

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val ResultWrapper<*>.succeeded
    get() = this is Success && data != null