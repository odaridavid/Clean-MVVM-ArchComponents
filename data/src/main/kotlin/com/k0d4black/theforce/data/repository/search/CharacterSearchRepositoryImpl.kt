package com.k0d4black.theforce.data.repository.search

import com.k0d4black.theforce.data.repository.search.CharacterSearchRepository
import com.k0d4black.theforce.data.source.CharacterSearchDataSource
import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
class CharacterSearchRepositoryImpl(private val characterSearchDataSource: CharacterSearchDataSource) :
    CharacterSearchRepository {

    override suspend fun getSearchedCharacters(params: String): List<SearchedCharacter> =
        characterSearchDataSource.query(params)


}