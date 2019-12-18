package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.CharacterSearchRepository


class CharacterSearchUseCase(
    private val searchRepository: CharacterSearchRepository
) {

    suspend fun searchCharacters(params: String) = searchRepository.searchCharacters(params)

}