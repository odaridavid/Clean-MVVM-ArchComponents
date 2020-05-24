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
class CharacterDetailsRemoteDataSource(private val apiService: StarWarsApiService) {

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of planet entity that can be used in the data layer
     */
    suspend fun getCharacterPlanet(characterUrl: String): Flow<PlanetEntity> = flow {
        val planetResponse = apiService.getPlanet(characterUrl)
        val planet = apiService.getPlanetDetails(planetResponse.homeworldUrl)
        emit(planet.toEntity())
    }

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of a list of specie entities that can be used in the data layer.Emits the whole
     * list once ready.
     */
    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<SpecieEntity>> = flow {
        val speciesResponse = apiService.getSpecies(characterUrl)
        val species = mutableListOf<SpecieEntity>()
        for (specieUrl in speciesResponse.specieUrls) {
            val specie = apiService.getSpecieDetails(specieUrl)
            species.add(specie.toEntity())
        }
        emit(species)
    }

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of film entities that can be used in the data layer.Each film is emitted
     * immediately its received from the API.
     */
    suspend fun getCharacterFilms(characterUrl: String): Flow<FilmEntity> = flow {
        val filmsResponse = apiService.getFilms(characterUrl)
        for (filmUrl in filmsResponse.filmUrls) {
            val film = apiService.getFilmDetails(filmUrl)
            emit(film.toEntity())
        }
    }

}