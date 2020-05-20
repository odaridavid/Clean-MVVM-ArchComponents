package com.k0d4black.theforce.domain.usecases


import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import javax.inject.Inject


class GetFilmsUseCase @Inject constructor(
    private val characterDetailsRepository: ICharacterDetailsRepository
) {

    suspend operator fun invoke(characterUrl: String) =
        characterDetailsRepository.getCharacterFilms(characterUrl)
}