package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import org.koin.core.KoinComponent

class SearchCharactersUseCase(
    private val searchRepository: ICharacterSearchRepository
): KoinComponent {

    suspend operator fun invoke(characterName: String) = searchRepository.searchCharacters(characterName)

}