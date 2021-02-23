package com.k0d4black.theforce.remote.charactersearch.data

import com.k0d4black.theforce.remote.charactersearch.mappers.CharacterSearchResponseMapper
import com.k0d4black.theforce.remote.isSuccessfulAndNotNull

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
                val character = characterSearchResponseMapper.mapToDomain(
                    characterResponse = characterResponse
                )
                characters.add(character)
            }
        }

        emit(value = characters)
    }
}
