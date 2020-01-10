package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.models.CharacterBasicInfoDomainModel
import com.k0d4black.theforce.domain.models.CharacterFilmDomainModel
import com.k0d4black.theforce.domain.models.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.models.CharacterSpeciesDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing character details
 */
class CharacterDetailsRepository @Inject constructor(
    private val characterDetailsDataSource: CharacterDetailsDataSource
) : ICharacterDetailsRepository {

    override suspend fun getCharacterDetails(characterId: Int): Flow<CharacterBasicInfoDomainModel> {
        return characterDetailsDataSource.getCharacterBasicDetails(characterId)
    }

    override suspend fun getCharacterPlanet(characterId: Int): Flow<CharacterPlanetDomainModel> {
        return characterDetailsDataSource.getCharacterPlanet(characterId)
    }

    override suspend fun getCharacterSpecies(characterId: Int): Flow<List<CharacterSpeciesDomainModel>> {
        return characterDetailsDataSource.getCharacterSpecies(characterId)
    }

    override suspend fun getCharacterFilms(characterId: Int): Flow<List<CharacterFilmDomainModel>> {
        return characterDetailsDataSource.getCharacterFilms(characterId)
    }

}