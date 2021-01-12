package com.k0d4black.theforce.remote.character.search.data

import com.k0d4black.theforce.remote.character.search.mappers.CharacterSearchResponseMapper
import com.k0d4black.theforce.shared.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepositoryImpl(
    private val apiService: CharacterSearchApiService,
    private val characterSearchResponseMapper: CharacterSearchResponseMapper
) : CharacterSearchRepository {

    override suspend fun search(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.search(characterName = characterName)
        val characters = mutableListOf<Character>()
        for (characterResponse in searchResponse.results) {
            val character = characterSearchResponseMapper.toDomain(
                characterResponse = characterResponse
            )
            characters.add(character)
        }
        emit(value = characters)
    }
}