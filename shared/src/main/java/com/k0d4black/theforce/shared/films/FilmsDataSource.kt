package com.k0d4black.theforce.shared.films

import kotlinx.coroutines.flow.Flow

interface FilmsDataSource {

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>
}
