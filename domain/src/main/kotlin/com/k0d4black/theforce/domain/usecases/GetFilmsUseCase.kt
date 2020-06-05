package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

typealias GetFilmsBaseUseCase = BaseUseCase<String, Flow<List<Film>>>

class GetFilmsUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : GetFilmsBaseUseCase {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterFilms(params)
}