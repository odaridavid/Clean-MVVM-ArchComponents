package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

class SearchCharactersUseCase(
    private val searchRepository: ICharacterSearchRepository
) : BaseUseCase<String, Flow<List<Character>>>, KoinComponent {

    override suspend operator fun invoke(params: String) =
        searchRepository.searchCharacters(params)

}