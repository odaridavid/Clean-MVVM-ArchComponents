package com.k0d4black.theforce.data.usecases

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.domain.CharacterFilmDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterFilmsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) : UseCase<Int, Flow<List<CharacterFilmDomainModel>>> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(characterId: Int) =
        characterDetailsRepository.getCharacterFilms(characterId)
}