package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.CharacterSearchDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterSearchUseCase @Inject constructor(
    private val searchRepository: ICharacterSearchRepository
) : UseCase<String, Flow<List<CharacterSearchDomainModel>>> {

    override suspend fun execute(params: String) = searchRepository.searchCharacters(params)

}