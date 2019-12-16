package com.k0d4black.theforce.data.repository.search

import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
interface CharacterSearchRepository {

    suspend fun getSearchedCharacters(params: String): List<SearchedCharacter>

}