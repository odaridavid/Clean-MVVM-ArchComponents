package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.CharacterBasicInfoDomainModel
import com.k0d4black.theforce.domain.models.CharacterFilmDomainModel
import com.k0d4black.theforce.domain.models.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.models.CharacterSpeciesDomainModel
import kotlinx.coroutines.flow.Flow


interface ICharacterDetailsRepository {

    suspend fun getCharacterDetails(characterId: Int): Flow<CharacterBasicInfoDomainModel>

    suspend fun getCharacterPlanet(characterId: Int): Flow<CharacterPlanetDomainModel>

    suspend fun getCharacterSpecies(characterId: Int): Flow<List<CharacterSpeciesDomainModel>>

    suspend fun getCharacterFilms(characterId: Int): Flow<List<CharacterFilmDomainModel>>

}