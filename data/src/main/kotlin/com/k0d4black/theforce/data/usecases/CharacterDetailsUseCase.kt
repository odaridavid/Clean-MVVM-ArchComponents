package com.k0d4black.theforce.data.usecases


import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import javax.inject.Inject

/**
 * Acts as a proxy between the presentation layer requesting character details and data layer
 * providing the character details
 */
class CharacterDetailsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {

    suspend fun getCharacterDetails(characterId: Int) =
        characterDetailsRepository.getCharacterDetails(characterId)

}