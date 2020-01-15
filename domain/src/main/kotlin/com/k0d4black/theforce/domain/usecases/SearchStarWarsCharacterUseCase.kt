package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchStarWarsCharacterUseCase @Inject constructor(
    private val searchRepository: ICharacterSearchRepository
) : UseCase<String, Flow<List<StarWarsCharacter>>> {

    override suspend fun execute(params: String) = searchRepository.searchCharacters(params)

}