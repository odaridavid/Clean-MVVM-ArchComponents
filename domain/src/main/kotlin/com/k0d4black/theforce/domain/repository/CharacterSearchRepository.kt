package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.SearchedCharacterDomainModel


interface CharacterSearchRepository {

    suspend fun searchCharacters(params: String): List<SearchedCharacterDomainModel>

}