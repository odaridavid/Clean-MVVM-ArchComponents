package com.k0d4black.theforce.feature.character.details

import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

typealias GetPlanetBaseUseCase = BaseUseCase<String, Flow<Planet>>

class GetPlanetUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : GetPlanetBaseUseCase {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterPlanet(params)
}