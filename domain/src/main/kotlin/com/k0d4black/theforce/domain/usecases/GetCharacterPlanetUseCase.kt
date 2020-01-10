package com.k0d4black.theforce.domain.usecases


import com.k0d4black.theforce.domain.models.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharacterPlanetUseCase @Inject constructor(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : UseCase<Int, Flow<CharacterPlanetDomainModel>> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(characterId: Int) =
        characterDetailsRepository.getCharacterPlanet(characterId)
}