package com.k0d4black.theforce.data.usecases


import com.k0d4black.theforce.data.repository.CharacterSearchRepository
import com.k0d4black.theforce.domain.CharacterSearchDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterSearchUseCase @Inject constructor(
    private val searchRepository: CharacterSearchRepository
) : UseCase<String, Flow<List<CharacterSearchDomainModel>>> {

    override suspend fun execute(params: String) = searchRepository.searchCharacters(params)

}