package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Co-ordinates data sources exposing character details
 */
class CharacterDetailsRepository(
    private val characterDetailsDataSource: CharacterDetailsDataSource
) : ICharacterDetailsRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> {
        return characterDetailsDataSource.getCharacterPlanet(characterUrl)
            .map { planet -> planet.toDomain() }
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> {
        return characterDetailsDataSource.getCharacterSpecies(characterUrl)
            .map { species -> species.map { eachSpecie -> eachSpecie.toDomain() } }
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> {
        return characterDetailsDataSource.getCharacterFilms(characterUrl)
            .map { films -> films.map { eachFilm -> eachFilm.toDomain() } }
    }

}