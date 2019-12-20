package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entities.*
import com.k0d4black.theforce.domain.models.*


internal fun SearchedCharacterDataModel.toDomain(): SearchedCharacterDomainModel {
    return SearchedCharacterDomainModel(
        this.name,
        this.species?.map { it.toDomain() } ?: emptyList(),
        this.url
    )
}

internal fun CharacterDetailsDataModel.toDomain(): CharacterDetailsDomainModel {
    return CharacterDetailsDomainModel(
        this.name,
        this.birthYear,
        this.height,
        this.species?.map { it.toDomain() } ?: emptyList(),
        this.films?.map { it.toDomain() } ?: emptyList(),
        this.homeworld?.toDomain()
    )
}

internal fun FilmDataModel.toDomain(): FilmDomainModel = FilmDomainModel(this.openingCrawl)


internal fun PlanetDataModel.toDomain(): PlanetDomainModel = PlanetDomainModel(this.name, this.population)


internal fun SpeciesDataModel.toDomain(): SpeciesDomainModel = SpeciesDomainModel(this.language)

