package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.*
import com.k0d4black.theforce.domain.*


internal fun SearchedCharacterDataModel.toDomain(): SearchedCharacterDomainModel {
    return SearchedCharacterDomainModel(
        this.name,
        this.birthYear,
        this.url
    )
}

internal fun CharacterDetailsDataModel.toDomain(): CharacterDetailsDomainModel {
    return CharacterDetailsDomainModel(
        this.name,
        this.birthYear,
        this.height
    )
}

internal fun FilmDataModel.toDomain(): FilmDomainModel =
    FilmDomainModel(this.title, this.openingCrawl)


internal fun PlanetDataModel.toDomain(): PlanetDomainModel =
    PlanetDomainModel(
        this.name,
        this.population
    )


internal fun SpeciesDataModel.toDomain(): SpeciesDomainModel =
    SpeciesDomainModel(this.name, this.language)

