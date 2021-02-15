package com.k0d4black.theforce.feature.charactersearchresults.mappers

import com.k0d4black.theforce.feature.charactersearchresults.model.CharacterSearchResultPresentation
import com.k0d4black.theforce.shared.model.Character

class CharacterPresentationMapper {

    fun mapFromDomain(character: Character): CharacterSearchResultPresentation = with(character) {
        CharacterSearchResultPresentation(
            name = name,
            birthYear = birthYear,
            height = height
        )
    }

}
