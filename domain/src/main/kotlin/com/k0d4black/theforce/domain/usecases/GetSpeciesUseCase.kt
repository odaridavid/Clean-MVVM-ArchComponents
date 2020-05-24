package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

class GetSpeciesUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : BaseUseCase<String, Flow<List<Specie>>>, KoinComponent {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterSpecies(params)

}