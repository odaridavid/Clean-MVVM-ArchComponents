package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toEntity
import com.k0d4black.theforce.data.models.entity.FilmEntity
import com.k0d4black.theforce.data.models.entity.PlanetEntity
import com.k0d4black.theforce.data.models.entity.SpecieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Gets character details data from network resource
 */
class CharacterDetailsDataSource(private val apiService: StarWarsApiService) {

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of planet entity that can be used in the data layer
     */
    suspend fun getCharacterPlanet(characterUrl: String): Flow<PlanetEntity> {
        val planetResponse = apiService.getPlanet(characterUrl)
        return flow {
            emit(apiService.getPlanetDetails(planetResponse.homeworldUrl).toEntity())
        }
    }

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of a list of specie entities that can be used in the data layer
     */
    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<SpecieEntity>> {
        val speciesResponse = apiService.getSpecies(characterUrl)
        val species = mutableListOf<SpecieEntity>()
        for (specieUrl in speciesResponse.specieUrls) {
            val specie = apiService.getSpecieDetails(specieUrl)
            species.add(specie.toEntity())
        }
        return flow { emit(species) }
    }

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of a list of film entities that can be used in the data layer
     */
    suspend fun getCharacterFilms(characterUrl: String): Flow<List<FilmEntity>> {
        val filmsResponse = apiService.getFilms(characterUrl)
        val films = mutableListOf<FilmEntity>()
        for (filmUrl in filmsResponse.filmUrls) {
            val film = apiService.getFilmDetails(filmUrl)
            films.add(film.toEntity())
        }
        return flow { emit(films) }
    }

}