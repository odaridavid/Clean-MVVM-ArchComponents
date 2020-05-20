package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val searchRepository: ICharacterSearchRepository
) {

    suspend operator fun invoke(params: String) = searchRepository.searchCharacters(params)

}