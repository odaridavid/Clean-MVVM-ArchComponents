package com.k0d4black.theforce.data.usecases


import com.k0d4black.theforce.data.repository.CharacterSearchRepository
import javax.inject.Inject

/**
 * Interacts with the presentation layer to pass the search queries and retrieve results.
 */
class CharacterSearchUseCase @Inject constructor(
    private val searchRepository: CharacterSearchRepository
) {

    suspend fun searchCharacters(params: String) = searchRepository.searchCharacters(params)

}