package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.CharacterDetailsRepository


class GetCharacterDetailsUseCase(
    private val characterDetailsRepository: CharacterDetailsRepository
) {

    suspend fun getCharacterDetails(characterId: Int) =
        characterDetailsRepository.getCharacterDetails(characterId)

}