package com.github.odaridavid.remote.character.details

import com.k0d4black.theforce.data.remote.api.StarWarsApiService
import com.k0d4black.theforce.data.remote.enforceHttps
import com.k0d4black.theforce.data.remote.mappers.toDomain
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDetailsRepository(
    private val apiService: StarWarsApiService
) : ICharacterDetailsRepository {

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow {
        val planetResponse = apiService.getPlanet(characterUrl.enforceHttps())
        val planet = apiService.getPlanetDetails(planetResponse.homeworldUrl.enforceHttps())
        emit(planet.toDomain())
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> = flow {
        val speciesResponse = apiService.getSpecies(characterUrl.enforceHttps())
        val species = mutableListOf<Specie>()
        for (specieUrl in speciesResponse.specieUrls) {
            val specie = apiService.getSpecieDetails(specieUrl.enforceHttps())
            species.add(specie.toDomain())
        }
        emit(species)
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmsResponse = apiService.getFilms(characterUrl.enforceHttps())
        val films = mutableListOf<Film>()
        for (filmUrl in filmsResponse.filmUrls) {
            val film = apiService.getFilmDetails(filmUrl.enforceHttps())
            films.add(film.toDomain())
        }
        emit(films)
    }
}