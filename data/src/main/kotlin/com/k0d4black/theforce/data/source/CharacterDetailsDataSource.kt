package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.models.CharacterDetailsDataModel
import com.k0d4black.theforce.data.models.CharacterFilmDataModel
import com.k0d4black.theforce.data.models.CharacterPlanetDataModel
import com.k0d4black.theforce.data.models.CharacterSpeciesDataModel
import com.k0d4black.theforce.domain.models.CharacterBasicInfoDomainModel
import com.k0d4black.theforce.domain.models.CharacterFilmDomainModel
import com.k0d4black.theforce.domain.models.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.models.CharacterSpeciesDomainModel
import com.k0d4black.theforce.domain.utils.id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Gets character details data from network resource
 */
class CharacterDetailsDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    suspend fun getCharacterBasicDetails(characterId: Int): Flow<CharacterBasicInfoDomainModel> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterDataModel =
            CharacterDetailsDataModel(
                birthYear = characterDetailsResponse.birthYear,
                name = characterDetailsResponse.name,
                height = characterDetailsResponse.height
            )
        return flow { emit(characterDataModel.toDomain()) }
    }


    suspend fun getCharacterPlanet(characterId: Int): Flow<CharacterPlanetDomainModel> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val planetResponse = apiService.getPlanet(characterDetailsResponse.homeWorld.id)
        val planetDataModel = CharacterPlanetDataModel(planetResponse.name, planetResponse.population)
        return flow { emit(planetDataModel.toDomain()) }
    }


    suspend fun getCharacterSpecies(characterId: Int): Flow<List<CharacterSpeciesDomainModel>> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterSpecies = mutableListOf<CharacterSpeciesDataModel>()
        for (specie in characterDetailsResponse.species) {
            val specieResponse = apiService.getSpecies(specie.id)
            val speciesDataModel =
                CharacterSpeciesDataModel(specieResponse.name, specieResponse.language)
            characterSpecies.add(speciesDataModel)
        }
        return flow { emit(characterSpecies.map { it.toDomain() }) }
    }


    suspend fun getCharacterFilms(characterId: Int): Flow<List<CharacterFilmDomainModel>> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterFilms = mutableListOf<CharacterFilmDataModel>()
        for (film in characterDetailsResponse.films) {
            val filmResponse = apiService.getFilms(film.id)
            val filmDataModel = CharacterFilmDataModel(filmResponse.title, filmResponse.openingCrawl)
            characterFilms.add(filmDataModel)
        }
        return flow { emit(characterFilms.map { it.toDomain() }) }
    }

}