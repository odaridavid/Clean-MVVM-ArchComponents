package com.k0d4black.theforce.data.repository.detail

import com.k0d4black.theforce.domain.CharacterDetails
import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
interface CharacterDetailRepository {

    suspend fun getCharacterDetails(searchedCharacter: SearchedCharacter): CharacterDetails
}

