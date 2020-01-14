package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies
import kotlinx.coroutines.flow.Flow


interface ICharacterDetailsRepository {

    suspend fun getCharacterDetails(characterId: Int): Flow<StarWarsCharacter>

    suspend fun getCharacterPlanet(characterId: Int): Flow<StarWarsCharacterPlanet>

    suspend fun getCharacterSpecies(characterId: Int): Flow<List<StarWarsCharacterSpecies>>

    suspend fun getCharacterFilms(characterId: Int): Flow<List<StarWarsCharacterFilm>>

}