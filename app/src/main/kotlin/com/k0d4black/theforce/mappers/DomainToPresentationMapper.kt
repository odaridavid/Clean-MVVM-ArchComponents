package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.domain.*
import com.k0d4black.theforce.models.*
import com.k0d4black.theforce.utils.convertToInches


fun SearchedCharacterDomainModel.toPresentation(): SearchedCharacterPresentationModel {
    return SearchedCharacterPresentationModel(
        this.name,
        this.birthYear,
        this.url
    )
}

fun CharacterDetailsDomainModel.toPresentation(): CharacterDetailsPresentationModel {
    return CharacterDetailsPresentationModel(
        this.name,
        this.birthYear,
        this.height,
        convertToInches(this.height)
    )
}

fun PlanetDomainModel.toPresentation(): PlanetPresentationModel {
    return PlanetPresentationModel(this.name, this.population)
}

fun FilmDomainModel.toPresentation(): FilmPresentationModel {
    return FilmPresentationModel(this.title, this.openingCrawl)
}

fun SpeciesDomainModel.toPresentation(): SpeciesPresentationModel {
    return SpeciesPresentationModel(this.name, this.language)
}