package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.CharacterSearchDomainModel
import kotlinx.coroutines.flow.Flow

interface ICharacterSearchRepository {
    suspend fun searchCharacters(params: String): Flow<List<CharacterSearchDomainModel>>
}