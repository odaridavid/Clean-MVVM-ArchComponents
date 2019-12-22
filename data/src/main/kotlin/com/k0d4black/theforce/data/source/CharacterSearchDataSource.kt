package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.models.SearchedCharacterDataModel
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import javax.inject.Inject

class CharacterSearchDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    suspend fun query(params: String): ResultWrapper<List<SearchedCharacterDataModel>> {
        return try {
            val searchResponse = apiService.searchCharacters(params)
            val searchDataModels = mutableListOf<SearchedCharacterDataModel>()
            for (searchResult in searchResponse.results) {
                val searchedCharacterDataModel =
                    SearchedCharacterDataModel(
                        searchResult.name,
                        searchResult.birthYear,
                        searchResult.url
                    )
                searchDataModels.add(searchedCharacterDataModel)
            }
            Success(searchDataModels)
        } catch (e: Exception) {
            Error(e)
        }
    }


}