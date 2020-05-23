package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import org.koin.core.KoinComponent

class GetSpeciesUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
): KoinComponent {

    suspend operator fun invoke(characterUrl: String) =
        characterDetailsRepository.getCharacterSpecies(characterUrl)

}