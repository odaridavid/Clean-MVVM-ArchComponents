package com.k0d4black.theforce.domain.usecases

import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

typealias FilmsUseCase = BaseUseCase<String, Flow<Film>>

class GetFilmsUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : FilmsUseCase {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterFilms(params)
}