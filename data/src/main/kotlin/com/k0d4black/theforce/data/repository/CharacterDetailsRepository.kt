package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.CharacterBasicInfoDomainModel
import com.k0d4black.theforce.domain.CharacterFilmDomainModel
import com.k0d4black.theforce.domain.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.CharacterSpeciesDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing character details
 */
class CharacterDetailsRepository @Inject constructor(
    private val characterDetailsDataSource: CharacterDetailsDataSource
) {
    suspend fun getCharacterDetails(characterId: Int): Flow<CharacterBasicInfoDomainModel> {
        return characterDetailsDataSource.getCharacterBasicDetails(characterId)
    }

    suspend fun getCharacterPlanet(characterId: Int): Flow<CharacterPlanetDomainModel> {
        return characterDetailsDataSource.getCharacterPlanet(characterId)
    }

    suspend fun getCharacterSpecies(characterId: Int): Flow<List<CharacterSpeciesDomainModel>> {
        return characterDetailsDataSource.getCharacterSpecies(characterId)
    }

    suspend fun getCharacterFilms(characterId: Int): Flow<List<CharacterFilmDomainModel>> {
        return characterDetailsDataSource.getCharacterFilms(characterId)
    }

}