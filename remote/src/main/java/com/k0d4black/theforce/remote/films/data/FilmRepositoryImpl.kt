package com.k0d4black.theforce.remote.films.data

import com.k0d4black.theforce.remote.core.isSuccessfulAndNotNull
import com.k0d4black.theforce.remote.films.mappers.FilmDetailsResponseMapper
import com.k0d4black.theforce.shared.enforceHttps
import com.k0d4black.theforce.shared.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl(
    private val apiService: FilmsApiService,
    private val filmDetailsResponseMapper: FilmDetailsResponseMapper
) : FilmRepository {

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow {
        val filmUrlsResponse = apiService.getFilmUrls(characterUrl = characterUrl.enforceHttps())
        if (!filmUrlsResponse.isSuccessfulAndNotNull()) return@flow

        val films = mutableListOf<Film>()
        filmUrlsResponse.body()?.run {
            for (filmUrl in filmUrls) {
                val filmDetailsResponse = apiService.getFilmDetails(
                    filmUrl = filmUrl.enforceHttps()
                )
                if (!filmDetailsResponse.isSuccessfulAndNotNull()) return@run
                val film = filmDetailsResponseMapper.mapToDomain(
                    filmDetailsResponse = filmDetailsResponse.body()!!
                )
                films.add(film)
            }
        }
        emit(value = films)
    }
}
