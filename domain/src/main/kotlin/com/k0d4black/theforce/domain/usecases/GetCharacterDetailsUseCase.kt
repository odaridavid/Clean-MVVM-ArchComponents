package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.CharacterDetailsRepository

/**
 * Acts as a proxy between the presentation layer requesting character details and data layer
 * providing the character details
 */
class GetCharacterDetailsUseCase(private val characterDetailsRepository: CharacterDetailsRepository) {

    suspend fun getCharacterDetails(characterId: Int) =
        characterDetailsRepository.getCharacterDetails(characterId)

}