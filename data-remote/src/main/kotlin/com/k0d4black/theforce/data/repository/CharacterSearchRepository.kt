package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.CharacterSearchRemoteDataSource
import com.k0d4black.theforce.data.source.ICharacterSearchRemoteDataSource
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Co-ordinates data sources exposing search results
 */
class CharacterSearchRepository(
    private val characterSearchRemoteDataSource: ICharacterSearchRemoteDataSource
) : ICharacterSearchRepository {

    override suspend fun searchCharacters(characterName: String): Flow<List<Character>> {
        return characterSearchRemoteDataSource.query(characterName)
    }

}