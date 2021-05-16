package com.k0d4black.theforce.remote.search.mappers

import com.k0d4black.theforce.remote.search.models.CharacterResponse
import com.k0d4black.theforce.shared.characters.Character

class CharacterSearchResponseMapper {

    fun mapToDomain(characterResponse: CharacterResponse): Character =
        with(characterResponse) {
            Character(
                name = name,
                birthYear = birthYear,
                height = height,
                url = url
            )
        }
}
