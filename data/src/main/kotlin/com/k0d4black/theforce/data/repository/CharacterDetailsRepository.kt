package com.k0d4black.theforce.data.repository

import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.data.source.CharacterDetailsDataSource
import com.k0d4black.theforce.domain.CharacterDetailsDomainModel
import com.k0d4black.theforce.domain.utils.Error
import com.k0d4black.theforce.domain.utils.ResultWrapper
import com.k0d4black.theforce.domain.utils.Success
import javax.inject.Inject


class CharacterDetailsRepository @Inject constructor(
    private val characterDetailsDataSource: CharacterDetailsDataSource
) {
    suspend fun getCharacterDetails(characterId: Int): ResultWrapper<CharacterDetailsDomainModel>? {
        return when (val results = characterDetailsDataSource.getCharacter(characterId)) {
            is Success -> Success(results.data.toDomain())
            is Error -> Error(results.exception)
            else -> throw IllegalStateException("Unknown Response")
        }
    }


}