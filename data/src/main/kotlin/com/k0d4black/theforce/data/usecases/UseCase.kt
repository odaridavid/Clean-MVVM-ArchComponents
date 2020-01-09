package com.k0d4black.theforce.data.usecases


interface UseCase<in P, out T> {

    suspend fun execute(params: P): T
}