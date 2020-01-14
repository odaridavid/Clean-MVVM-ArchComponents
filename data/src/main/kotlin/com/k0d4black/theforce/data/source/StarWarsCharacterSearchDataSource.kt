package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsCharacterSearchDataSource @Inject constructor(private val apiService: StarWarsApiService) {

    /**
     * Takes in [params] to be used for the search
     * @return list of search results
     */
    suspend fun query(params: String): Flow<List<StarWarsCharacterEntity>> {
        val searchResponse = apiService.searchCharacters(params)
        val searchDataModels = mutableListOf<StarWarsCharacterEntity>()
        for (searchResult in searchResponse.results) {
            searchDataModels.add(searchResult.toEntity())
        }
        return flow { emit(searchDataModels) }
    }

}