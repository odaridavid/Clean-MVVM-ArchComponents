package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import com.k0d4black.theforce.domain.models.CharacterSearchDomainModel
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Co-ordinates data sources exposing search results
 */
class CharacterSearchRepository @Inject constructor(
    private val characterSearchDataSource: CharacterSearchDataSource
) : ICharacterSearchRepository {

    override suspend fun searchCharacters(params: String): Flow<List<CharacterSearchDomainModel>> {
        return characterSearchDataSource.query(params)
    }

}