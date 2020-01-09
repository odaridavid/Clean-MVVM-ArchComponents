package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.domain.*
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.utils.convertToInches


fun CharacterSearchDomainModel.toPresentation(): CharacterSearchPresentationModel {
    return CharacterSearchPresentationModel(
        this.name,
        this.birthYear,
        this.url
    )
}

fun CharacterBasicInfoDomainModel.toPresentation(): CharacterDetailsPresentationModel {
    return CharacterDetailsPresentationModel(
        this.name,
        this.birthYear,
        this.height,
        convertToInches(this.height)
    )
}

fun CharacterPlanetDomainModel.toPresentation(): CharacterPlanetPresentationModel {
    return CharacterPlanetPresentationModel(this.name, this.population)
}

fun CharacterFilmDomainModel.toPresentation(): CharacterFilmPresentationModel {
    return CharacterFilmPresentationModel(this.title, this.openingCrawl)
}

fun CharacterSpeciesDomainModel.toPresentation(): CharacterSpeciesPresentationModel {
    return CharacterSpeciesPresentationModel(this.name, this.language)
}