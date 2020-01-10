package com.k0d4black.theforce.domain.usecases


import com.k0d4black.theforce.domain.models.CharacterBasicInfoDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharacterBasicInfoUseCase @Inject constructor(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : UseCase<Int, Flow<CharacterBasicInfoDomainModel>> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override suspend fun execute(characterId: Int) =
        characterDetailsRepository.getCharacterDetails(characterId)

}