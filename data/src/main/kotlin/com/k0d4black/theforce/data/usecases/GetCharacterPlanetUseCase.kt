package com.k0d4black.theforce.data.usecases

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.domain.CharacterPlanetDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterPlanetUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) : UseCase<Int, Flow<CharacterPlanetDomainModel>> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(characterId: Int) =
        characterDetailsRepository.getCharacterPlanet(characterId)
}