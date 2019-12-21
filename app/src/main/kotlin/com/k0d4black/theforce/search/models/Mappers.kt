package com.k0d4black.theforce.search.models

import com.k0d4black.theforce.domain.models.PlanetDomainModel
import com.k0d4black.theforce.domain.models.SearchedCharacterDomainModel


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