package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.StarWarsCharacterSearchDataSource
import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing search results
 */
class StarWarsCharacterSearchRepository @Inject constructor(
    private val starWarsCharacterSearchDataSource: StarWarsCharacterSearchDataSource
) : ICharacterSearchRepository {

    override suspend fun searchCharacters(params: String): Flow<List<StarWarsCharacter>> {
        return starWarsCharacterSearchDataSource.query(params).map { it.map { results->results.toDomain() } }
    }

}