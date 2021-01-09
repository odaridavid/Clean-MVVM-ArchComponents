package com.github.odaridavid.remote.character.search

import com.k0d4black.theforce.data.remote.api.StarWarsApiService
import com.k0d4black.theforce.data.remote.mappers.toDomain
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepository(
    private val apiService: StarWarsApiService
) : ICharacterSearchRepository {

    override suspend fun searchCharacters(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.searchCharacters(characterName)
        val starWarsCharacters = mutableListOf<Character>()
        for (starWarsCharacter in searchResponse.results) {
            starWarsCharacters.add(starWarsCharacter.toDomain())
        }
        emit(starWarsCharacters)
    }

}