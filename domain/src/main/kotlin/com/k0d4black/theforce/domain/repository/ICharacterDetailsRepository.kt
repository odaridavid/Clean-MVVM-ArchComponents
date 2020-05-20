package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import kotlinx.coroutines.flow.Flow


interface ICharacterDetailsRepository {

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>

}