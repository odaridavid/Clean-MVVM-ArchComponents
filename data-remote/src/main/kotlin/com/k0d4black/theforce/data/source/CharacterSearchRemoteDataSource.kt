package com.k0d4black.theforce.data.source

import com.k0d4black.theforce.data.api.StarWarsApiService
import com.k0d4black.theforce.data.mappers.toDomain
import com.k0d4black.theforce.domain.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ICharacterSearchRemoteDataSource {

    /**
     * Takes in a [characterName] to be used for the search
     *
     * @return [Flow] of StarWars characters domain objects.
     */
    suspend fun query(characterName: String): Flow<List<Character>>

}

class CharacterSearchRemoteDataSource(
    private val apiService: StarWarsApiService
) : ICharacterSearchRemoteDataSource {

    override suspend fun query(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.searchCharacters(characterName)
        val starWarsCharacters = mutableListOf<Character>()
        for (starWarsCharacter in searchResponse.results) {
            starWarsCharacters.add(starWarsCharacter.toDomain())
        }
        emit(starWarsCharacters)
    }

}