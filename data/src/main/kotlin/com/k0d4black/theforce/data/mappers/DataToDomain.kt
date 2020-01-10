package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.*
import com.k0d4black.theforce.domain.models.*


internal fun CharacterSearchDataModel.toDomain(): CharacterSearchDomainModel {
    return CharacterSearchDomainModel(
        this.name,
        this.birthYear,
        this.url
    )
}

internal fun CharacterDetailsDataModel.toDomain(): CharacterBasicInfoDomainModel {
    return CharacterBasicInfoDomainModel(
        this.name,
        this.birthYear,
        this.height
    )
}

internal fun CharacterFilmDataModel.toDomain(): CharacterFilmDomainModel =
    CharacterFilmDomainModel(
        this.title,
        this.openingCrawl
    )


internal fun CharacterPlanetDataModel.toDomain(): CharacterPlanetDomainModel =
    CharacterPlanetDomainModel(
        this.name,
        this.population
    )


internal fun CharacterSpeciesDataModel.toDomain(): CharacterSpeciesDomainModel =
    CharacterSpeciesDomainModel(
        this.name,
        this.language
    )

