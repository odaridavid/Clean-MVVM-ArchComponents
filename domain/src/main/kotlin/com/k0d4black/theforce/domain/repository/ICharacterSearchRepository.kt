package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterSearchRepository {
    suspend fun searchCharacters(characterName: String): Flow<List<Character>>
}