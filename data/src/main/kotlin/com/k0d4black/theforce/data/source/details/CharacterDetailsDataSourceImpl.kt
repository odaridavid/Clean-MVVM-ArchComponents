package com.k0d4black.theforce.data.source.details

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.models.entities.CharacterDetailsDataModel
import com.k0d4black.theforce.data.models.entities.FilmDataModel
import com.k0d4black.theforce.data.models.entities.PlanetDataModel
import com.k0d4black.theforce.data.models.entities.SpeciesDataModel
import com.k0d4black.theforce.data.models.response.CharacterDetailsResponse
import com.k0d4black.theforce.data.source.utils.populateSpeciesList
import com.k0d4black.theforce.domain.utils.id

class CharacterDetailsDataSourceImpl(private val apiService: StarWarsApiService) :
    CharacterDetailsDataSource {

    //TODO Better Exception Handling
    override suspend fun getCharacter(characterId: Int): CharacterDetailsDataModel? {
        return try {
            val characterDetailsResponse = apiService.getCharacterDetails(characterId)

            val characterDataModel = CharacterDetailsDataModel().apply {
                birthYear = characterDetailsResponse.birthYear
                name = characterDetailsResponse.name
                height = characterDetailsResponse.height
            }

            processFilms(characterDetailsResponse, characterDataModel)

            processSpecies(characterDetailsResponse, characterDataModel)

            processPlanet(characterDetailsResponse, characterDataModel)

            characterDataModel
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun processPlanet(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {
        val planetResponse = apiService.getPlanet(characterDetailsResponse.homeWorld.id)
        val planetDataModel = PlanetDataModel(planetResponse.name, planetResponse.population)
        characterDataModel.homeworld = planetDataModel
    }

    private suspend fun processSpecies(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {
        val characterSpecies = mutableListOf<SpeciesDataModel>()

        populateSpeciesList(characterSpecies, characterDetailsResponse.species, apiService)

        characterDataModel.species = characterSpecies
    }

    private suspend fun processFilms(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {
        val characterFilms = mutableListOf<FilmDataModel>()
        for (film in characterDetailsResponse.films) {
            val filmResponse = apiService.getFilms(film.id)
            val filmDataModel = FilmDataModel(filmResponse.openingCrawl)
            characterFilms.add(filmDataModel)
        }
        characterDataModel.films = characterFilms
    }


}