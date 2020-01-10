package com.k0d4black.theforce.domain.usecases


/**
 * Base UseCase Takes in input type and expected output type
 */
interface UseCase<in P, out T> {

    suspend fun execute(params: P): T
}