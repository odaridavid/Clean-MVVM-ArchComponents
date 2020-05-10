package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies
import com.k0d4black.theforce.models.StarWarsCharacterFilmsUiModel
import com.k0d4black.theforce.models.StarWarsCharacterPlanetUiModel
import com.k0d4black.theforce.models.StarWarsCharacterSpeciesUiModel
import com.k0d4black.theforce.models.StarWarsCharacterUiModel
import com.k0d4black.theforce.commons.convertToInches


fun StarWarsCharacter.toPresentation(): StarWarsCharacterUiModel {
    return StarWarsCharacterUiModel(
        this.name,
        this.birthYear,
        this.height,
        convertToInches(this.height),
        this.url
    )
}

fun StarWarsCharacterPlanet.toPresentation(): StarWarsCharacterPlanetUiModel {
    return StarWarsCharacterPlanetUiModel(this.name, this.population)
}

fun StarWarsCharacterFilm.toPresentation(): StarWarsCharacterFilmsUiModel {
    return StarWarsCharacterFilmsUiModel(this.title, this.openingCrawl)
}

fun StarWarsCharacterSpecies.toPresentation(): StarWarsCharacterSpeciesUiModel {
    return StarWarsCharacterSpeciesUiModel(this.name, this.language)
}