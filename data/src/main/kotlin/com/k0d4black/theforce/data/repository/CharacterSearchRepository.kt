package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import com.k0d4black.theforce.domain.SearchedCharacterDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing search results
 */
class CharacterSearchRepository @Inject constructor(
    private val characterSearchDataSource: CharacterSearchDataSource
) {

    suspend fun searchCharacters(params: String): Flow<List<SearchedCharacterDomainModel>> {
        return characterSearchDataSource.query(params)
    }

}