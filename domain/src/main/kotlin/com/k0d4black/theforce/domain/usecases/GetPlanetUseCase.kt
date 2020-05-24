package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

typealias PlanetUseCase = BaseUseCase<String, Flow<Planet>>

class GetPlanetUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : PlanetUseCase, KoinComponent {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterPlanet(params)
}