package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.search.CharacterSearchDataSource
import com.k0d4black.theforce.domain.models.SearchedCharacterDomainModel
import com.k0d4black.theforce.domain.repository.CharacterSearchRepository

private class CharacterSearchRepositoryImpl(val characterSearchDataSource: CharacterSearchDataSource) :
    CharacterSearchRepository {

    override suspend fun searchCharacters(params: String): List<SearchedCharacterDomainModel> =
        characterSearchDataSource.query(params).map { it.toDomain() }

}