package com.k0d4black.theforce.remote.films.data

import com.k0d4black.theforce.shared.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>
}
