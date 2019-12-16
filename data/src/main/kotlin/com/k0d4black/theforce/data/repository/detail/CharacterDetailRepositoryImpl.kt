package com.k0d4black.theforce.data.repository.detail

import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.CharacterDetails
import com.k0d4black.theforce.domain.SearchedCharacter

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
class CharacterDetailRepositoryImpl(private val characterDetailsDataSource: CharacterDetailsDataSource) :
    CharacterDetailRepository {

    override suspend fun getCharacterDetails(searchedCharacter: SearchedCharacter): CharacterDetails =
        characterDetailsDataSource.getCharacter(searchedCharacter)


}