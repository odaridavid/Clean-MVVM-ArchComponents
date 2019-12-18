package com.k0d4black.theforce.data.source.search

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.models.entities.SearchedCharacterDataModel
import com.k0d4black.theforce.data.models.entities.SpeciesDataModel
import com.k0d4black.theforce.data.source.utils.populateSpeciesList

class CharacterSearchDataSourceImpl(private val apiService: StarWarsApiService) :
    CharacterSearchDataSource {

    override suspend fun query(params: String): List<SearchedCharacterDataModel> {
        return try {
            val searchResponse = apiService.searchCharacters(params)
            val searchDataModels = mutableListOf<SearchedCharacterDataModel>()
            val characterSpecies = mutableListOf<SpeciesDataModel>()
            for (searchResult in searchResponse.results) {
                val searchedCharacterDataModel = SearchedCharacterDataModel().apply {
                    name = searchResult.name
                    url = searchResult.url
                }

                populateSpeciesList(characterSpecies, searchResult.species, apiService)

                searchedCharacterDataModel.species = characterSpecies

                searchDataModels.add(searchedCharacterDataModel)
            }

            searchDataModels

        } catch (e: Exception) {
            emptyList()
        }
    }


}