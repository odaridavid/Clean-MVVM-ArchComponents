package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies
import kotlinx.coroutines.flow.Flow


interface ICharacterDetailsRepository {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<StarWarsCharacterPlanet>

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<StarWarsCharacterSpecies>>

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<StarWarsCharacterFilm>>

}