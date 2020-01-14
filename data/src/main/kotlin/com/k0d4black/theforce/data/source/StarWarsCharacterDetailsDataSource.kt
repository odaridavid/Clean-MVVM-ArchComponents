package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterFilmEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterPlanetEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterSpeciesEntity
import com.k0d4black.theforce.domain.utils.id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Gets character details data from network resource
 */
class StarWarsCharacterDetailsDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    suspend fun getCharacterBasicDetails(characterId: Int): Flow<StarWarsCharacterEntity> {
        return flow { emit(apiService.getCharacterDetails(characterId).toEntity()) }
    }


    suspend fun getCharacterPlanet(characterId: Int): Flow<StarWarsCharacterPlanetEntity> {
        val characterDetailsHomeWorldResponse = apiService.getCharacterHomeworld(characterId)
        return flow { emit(apiService.getPlanet(characterDetailsHomeWorldResponse.homeWorld.id).toEntity()) }
    }


    suspend fun getCharacterSpecies(characterId: Int): Flow<List<StarWarsCharacterSpeciesEntity>> {
        val characterDetailsResponse = apiService.getCharacterSpecies(characterId)
        val characterSpecies = mutableListOf<StarWarsCharacterSpeciesEntity>()
        for (specie in characterDetailsResponse.species) {
            val specieResponse = apiService.getSpecies(specie.id)
            characterSpecies.add(specieResponse.toEntity())
        }
        return flow { emit(characterSpecies) }
    }


    suspend fun getCharacterFilms(characterId: Int): Flow<List<StarWarsCharacterFilmEntity>> {
        val characterDetailsResponse = apiService.getCharacterFilms(characterId)
        val characterFilms = mutableListOf<StarWarsCharacterFilmEntity>()
        for (film in characterDetailsResponse.films) {
            val filmResponse = apiService.getFilms(film.id)
            characterFilms.add(filmResponse.toEntity())
        }
        return flow { emit(characterFilms) }
    }

}