package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import org.koin.core.KoinComponent

class GetPlanetUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
): KoinComponent {

    suspend operator fun invoke(characterUrl: String) =
        characterDetailsRepository.getCharacterPlanet(characterUrl)
}