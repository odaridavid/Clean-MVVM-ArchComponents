package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.models.CharacterDetailsDataModel
import com.k0d4black.theforce.data.models.FilmDataModel
import com.k0d4black.theforce.data.models.PlanetDataModel
import com.k0d4black.theforce.data.models.SpeciesDataModel
import com.k0d4black.theforce.data.models.response.CharacterDetailsResponse
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import com.k0d4black.theforce.domain.utils.id
import retrofit2.HttpException
import javax.inject.Inject

class CharacterDetailsDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    /**
     * This function requires improved exception handling by catching them
     * as opposed to using null values.
     *
     * Takes a [characterId]
     * @return a characters details
     *
     * In the event value returned is null it should
     * @throws HttpException
     */
    suspend fun getCharacter(characterId: Int): ResultWrapper<CharacterDetailsDataModel>? {
        return try {
            val characterDetailsResponse = apiService.getCharacterDetails(characterId)

            val characterDataModel =
                CharacterDetailsDataModel(
                    birthYear = characterDetailsResponse.birthYear,
                    name = characterDetailsResponse.name,
                    height = characterDetailsResponse.height,
                    species = null,
                    films = null,
                    homeworld = null
                )

            processFilms(characterDetailsResponse, characterDataModel)

            processSpecies(characterDetailsResponse, characterDataModel)

            processPlanet(characterDetailsResponse, characterDataModel)

            Success(characterDataModel)
        } catch (e: Exception) {
            Error(e)
        }
    }

    //TODO Refactor Dry Code
    private suspend fun processPlanet(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {
        val planetResponse = apiService.getPlanet(characterDetailsResponse.homeWorld.id)
        val planetDataModel =
            PlanetDataModel(
                planetResponse.name,
                planetResponse.population
            )
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
            val filmDataModel =
                FilmDataModel(
                    filmResponse.openingCrawl
                )
            characterFilms.add(filmDataModel)
        }
        characterDataModel.films = characterFilms
    }

    private suspend fun populateSpeciesList(
        species: MutableList<SpeciesDataModel>,
        speciesUrls: List<String>,
        apiService: StarWarsApiService
    ) {
        for (specie in speciesUrls) {
            val specieResponse = apiService.getSpecies(specie.id)
            val speciesDataModel =
                SpeciesDataModel(
                    specieResponse.language
                )
            species.add(speciesDataModel)
        }
    }


}