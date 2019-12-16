package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
interface CharacterSearchDataSource {

    suspend fun query(params: String):List<SearchedCharacter>

}