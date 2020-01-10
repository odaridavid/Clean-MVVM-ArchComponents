package com.k0d4black.theforce.domain.usecases


import com.k0d4black.theforce.domain.models.CharacterSpeciesDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterSpeciesUseCase @Inject constructor(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : UseCase<Int, Flow<List<CharacterSpeciesDomainModel>>> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(characterId: Int) =
        characterDetailsRepository.getCharacterSpecies(characterId)

}