package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.StarWarsCharacterDetailsDataSource
import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing character details
 */
class StarWarsCharacterDetailsRepository @Inject constructor(
    private val starWarsCharacterDetailsDataSource: StarWarsCharacterDetailsDataSource
) : ICharacterDetailsRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<StarWarsCharacterPlanet> {
        return starWarsCharacterDetailsDataSource.getCharacterPlanet(characterUrl)
            .map { it.toDomain() }
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<StarWarsCharacterSpecies>> {
        return starWarsCharacterDetailsDataSource.getCharacterSpecies(characterUrl)
            .map { it.map { specie -> specie.toDomain() } }
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<StarWarsCharacterFilm>> {
        return starWarsCharacterDetailsDataSource.getCharacterFilms(characterUrl)
            .map { it.map { film -> film.toDomain() } }
    }

}