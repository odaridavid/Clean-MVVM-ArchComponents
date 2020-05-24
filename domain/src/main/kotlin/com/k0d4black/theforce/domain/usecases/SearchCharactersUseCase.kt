package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

typealias SearchUseCase = BaseUseCase<String, Flow<List<Character>>>

class SearchCharactersUseCase(
    private val searchRepository: ICharacterSearchRepository
) : SearchUseCase, KoinComponent {

    override suspend operator fun invoke(params: String) =
        searchRepository.searchCharacters(params)

}