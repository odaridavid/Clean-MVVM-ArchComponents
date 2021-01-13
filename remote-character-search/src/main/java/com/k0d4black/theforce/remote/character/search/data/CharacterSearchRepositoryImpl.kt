package com.k0d4black.theforce.remote.character.search.data

import com.k0d4black.theforce.remote.character.search.mappers.CharacterSearchResponseMapper
import com.k0d4black.theforce.remote.core.isSuccessfulAndNotNull
import com.k0d4black.theforce.shared.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepositoryImpl(
    private val apiService: CharacterSearchApiService,
    private val characterSearchResponseMapper: CharacterSearchResponseMapper
) : CharacterSearchRepository {

    override suspend fun search(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.search(characterName = characterName)
        if (!searchResponse.isSuccessfulAndNotNull()) return@flow

        val characters = mutableListOf<Character>()
        searchResponse.body()?.run {
            for (characterResponse in results) {
                val character = characterSearchResponseMapper.toDomain(
                    characterResponse = characterResponse
                )
                characters.add(character)
            }
        }

        emit(value = characters)
    }
}
