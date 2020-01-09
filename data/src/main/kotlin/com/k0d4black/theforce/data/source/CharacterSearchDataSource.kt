package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.models.CharacterSearchDataModel
import com.k0d4black.theforce.domain.CharacterSearchDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterSearchDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    suspend fun query(params: String): Flow<List<CharacterSearchDomainModel>> {
        val searchResponse = apiService.searchCharacters(params)
        val searchDataModels = mutableListOf<CharacterSearchDataModel>()
        for (searchResult in searchResponse.results) {
            val searchedCharacterDataModel =
                CharacterSearchDataModel(
                    searchResult.name,
                    searchResult.birthYear,
                    searchResult.url
                )
            searchDataModels.add(searchedCharacterDataModel)
        }
        return flow { emit(searchDataModels.map { it.toDomain() }) }
    }

}