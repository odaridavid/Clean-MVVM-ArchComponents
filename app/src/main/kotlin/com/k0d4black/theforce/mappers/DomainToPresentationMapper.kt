package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.models.PlanetPresentationModel
import com.k0d4black.theforce.models.SearchedCharacterPresentationModel
import com.k0d4black.theforce.domain.PlanetDomainModel
import com.k0d4black.theforce.domain.SearchedCharacterDomainModel


fun PlanetDomainModel.toDomain(): PlanetPresentationModel {
    return PlanetPresentationModel(
        this.name,
        this.population
    )
}

fun SearchedCharacterDomainModel.toPresentation(): SearchedCharacterPresentationModel {
    return SearchedCharacterPresentationModel(
        this.name,
        this.birthYear,
        this.url
    )
}