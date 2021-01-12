package com.k0d4black.theforce.remote.core

import retrofit2.Response

fun <T> Response<T>.isSuccessfulAndNotNull(): Boolean = isSuccessful && body() != null