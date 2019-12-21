package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.search.CharacterSearchDataSource
import com.k0d4black.theforce.domain.models.SearchedCharacterDomainModel
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import javax.inject.Inject

class CharacterSearchRepository @Inject constructor(
    private val characterSearchDataSource: CharacterSearchDataSource
) {

    suspend fun searchCharacters(params: String): ResultWrapper<List<SearchedCharacterDomainModel>> {
        return when (val results = characterSearchDataSource.query(params)) {
            is Success -> Success(results.data.map { it.toDomain() })
            is Error -> Error(results.exception)
            else -> throw IllegalStateException("Unknown Response")
        }

    }

}