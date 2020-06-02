package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICharacterDetailsRemoteDataSource {

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of planet domain objects
     */
    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of list of species domain objects
     */
    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>

    /**
     * @param characterUrl an absolute url to the character information
     *
     * @return [Flow] of film domain objects
     */
    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>
}

/**
 * Gets character details data from network resource
 */
class CharacterDetailsRemoteDataSource(
    private val apiService: StarWarsApiService
) : ICharacterDetailsRemoteDataSource {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow {
        val planetResponse = apiService.getPlanet(characterUrl)
        val planet = apiService.getPlanetDetails(planetResponse.homeworldUrl)
        emit(planet.toDomain())
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> =
        flow {
            val speciesResponse = apiService.getSpecies(characterUrl)
            val species = mutableListOf<Specie>()
            for (specieUrl in speciesResponse.specieUrls) {
                val specie = apiService.getSpecieDetails(specieUrl)
                species.add(specie.toDomain())
            }
            emit(species)
        }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = apiService.getFilms(characterUrl)
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.filmUrls) {
            val film = apiService.getFilmDetails(filmUrl)
            films.add(film.toDomain())
        }
        emit(films)
    }

}