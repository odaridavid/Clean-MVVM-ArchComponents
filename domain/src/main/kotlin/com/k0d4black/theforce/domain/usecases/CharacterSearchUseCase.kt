package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.CharacterSearchRepository

/**
 * Interacts with the presentation layer to pass the search queries and retrieve results.
 */
class CharacterSearchUseCase(private val searchRepository: CharacterSearchRepository) {

    suspend fun searchCharacters(params: String) = searchRepository.searchCharacters(params)

}