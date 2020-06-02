package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.source.ICharacterDetailsRemoteDataSource
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Co-ordinates data sources exposing character details
 */
class CharacterDetailsRepository(
    private val characterDetailsRemoteDataSource: ICharacterDetailsRemoteDataSource
) : ICharacterDetailsRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> {
        return characterDetailsRemoteDataSource.getCharacterPlanet(characterUrl)
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> {
        return characterDetailsRemoteDataSource.getCharacterSpecies(characterUrl)
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> {
        return characterDetailsRemoteDataSource.getCharacterFilms(characterUrl)
    }

}