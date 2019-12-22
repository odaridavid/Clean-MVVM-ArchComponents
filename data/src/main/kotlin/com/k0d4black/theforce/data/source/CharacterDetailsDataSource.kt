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
import javax.inject.Inject

class CharacterDetailsDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    /**
     * This function requires improved exception handling by catching them
     * as opposed to using null values.
     *
     * Takes a [characterId]
     * @return result wrrapper on characters details
     *
     * In the event value returned is null it should
     * @throws an Exception
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

    /**
     * Gets data from planets endpoint and maps it to data model if errors occur a null reference will
     * be added to the list.
     */
    private suspend fun processPlanet(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {
        try {
            val planetResponse = apiService.getPlanet(characterDetailsResponse.homeWorld.id)
            val planetDataModel =
                PlanetDataModel(
                    planetResponse.name,
                    planetResponse.population
                )
            characterDataModel.homeworld = planetDataModel
        } catch (e: Exception) {
            characterDataModel.homeworld = null
        }
    }

    /**
     * Gets data from species endpoint and maps it to data model if errors occur a null reference will
     * be added to the list.
     */
    private suspend fun processSpecies(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {

        val characterSpecies = mutableListOf<SpeciesDataModel?>()

        populateSpeciesList(characterSpecies, characterDetailsResponse.species, apiService)

        characterDataModel.species = characterSpecies

    }

    /**
     * Gets data from films endpoint and maps it to data model if errors occur a null reference will
     * be added to the list.
     */
    private suspend fun processFilms(
        characterDetailsResponse: CharacterDetailsResponse,
        characterDataModel: CharacterDetailsDataModel
    ) {

        val characterFilms = mutableListOf<FilmDataModel?>()
        for (film in characterDetailsResponse.films) {
            try {
                val filmResponse = apiService.getFilms(film.id)
                val filmDataModel =
                    FilmDataModel(
                        filmResponse.title,
                        filmResponse.openingCrawl
                    )
                characterFilms.add(filmDataModel)
            } catch (e: Exception) {
                characterFilms.add(null)
            }
        }
        characterDataModel.films = characterFilms

    }

    private suspend fun populateSpeciesList(
        species: MutableList<SpeciesDataModel?>,
        speciesUrls: List<String>,
        apiService: StarWarsApiService
    ) {

        for (specie in speciesUrls) {
            try {
                val specieResponse = apiService.getSpecies(specie.id)
                val speciesDataModel =
                    SpeciesDataModel(
                        specieResponse.name,
                        specieResponse.language
                    )
                species.add(speciesDataModel)
            } catch (e: Exception) {
                species.add(null)
            }
        }
    }


}