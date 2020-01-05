package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.models.CharacterDetailsDataModel
import com.k0d4black.theforce.data.models.FilmDataModel
import com.k0d4black.theforce.data.models.PlanetDataModel
import com.k0d4black.theforce.data.models.SpeciesDataModel
import com.k0d4black.theforce.domain.CharacterDetailsDomainModel
import com.k0d4black.theforce.domain.FilmDomainModel
import com.k0d4black.theforce.domain.PlanetDomainModel
import com.k0d4black.theforce.domain.SpeciesDomainModel
import com.k0d4black.theforce.domain.utils.id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailsDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    suspend fun getCharacterDetails(characterId: Int): Flow<CharacterDetailsDomainModel> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterDataModel =
            CharacterDetailsDataModel(
                birthYear = characterDetailsResponse.birthYear,
                name = characterDetailsResponse.name,
                height = characterDetailsResponse.height
            )
        return flow { emit(characterDataModel.toDomain()) }
    }


    suspend fun getCharacterPlanet(characterId: Int): Flow<PlanetDomainModel> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val planetResponse = apiService.getPlanet(characterDetailsResponse.homeWorld.id)
        val planetDataModel = PlanetDataModel(planetResponse.name, planetResponse.population)
        return flow { emit(planetDataModel.toDomain()) }
    }


    suspend fun getCharacterSpecies(characterId: Int): Flow<List<SpeciesDomainModel>> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterSpecies = mutableListOf<SpeciesDataModel>()
        for (specie in characterDetailsResponse.species) {
            val specieResponse = apiService.getSpecies(specie.id)
            val speciesDataModel =
                SpeciesDataModel(specieResponse.name, specieResponse.language)
            characterSpecies.add(speciesDataModel)
        }
        return flow { emit(characterSpecies.map { it.toDomain() }) }
    }


    suspend fun getCharacterFilms(characterId: Int): Flow<List<FilmDomainModel>> {
        val characterDetailsResponse = apiService.getCharacterDetails(characterId)
        val characterFilms = mutableListOf<FilmDataModel>()
        for (film in characterDetailsResponse.films) {
            val filmResponse = apiService.getFilms(film.id)
            val filmDataModel = FilmDataModel(filmResponse.title, filmResponse.openingCrawl)
            characterFilms.add(filmDataModel)
        }
        return flow { emit(characterFilms.map { it.toDomain() }) }
    }

}