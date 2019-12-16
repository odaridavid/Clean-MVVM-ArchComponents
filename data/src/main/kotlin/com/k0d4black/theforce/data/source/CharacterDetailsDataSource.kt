package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.domain.CharacterDetails
import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 * Provides Abstraction to data source
 **/
interface CharacterDetailsDataSource {

    suspend fun getCharacter(searchedCharacter: SearchedCharacter): CharacterDetails

}