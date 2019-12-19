package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.details.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.models.CharacterDetailsDomainModel
import com.k0d4black.theforce.domain.repository.CharacterDetailsRepository


private class CharacterDetailsRepositoryImpl(
     val characterDetailsDataSource: CharacterDetailsDataSource
) : CharacterDetailsRepository {

    override suspend fun getCharacterDetails(characterId: Int): CharacterDetailsDomainModel? =
        characterDetailsDataSource.getCharacter(characterId)?.toDomain()

}