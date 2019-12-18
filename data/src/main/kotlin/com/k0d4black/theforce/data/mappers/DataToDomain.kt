package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entities.*
import com.k0d4black.theforce.domain.models.*


fun SearchedCharacterDataModel.toDomain(): SearchedCharacterDomainModel {
    return SearchedCharacterDomainModel(
        this.name,
        this.species?.map { it.toDomain() } ?: emptyList(),
        this.url
    )
}

fun CharacterDetailsDataModel.toDomain(): CharacterDetailsDomainModel {
    return CharacterDetailsDomainModel(
        this.name,
        this.birthYear,
        this.height,
        this.species?.map { it.toDomain() } ?: emptyList(),
        this.films?.map { it.toDomain() } ?: emptyList(),
        this.homeworld?.toDomain()
    )
}

fun FilmDataModel.toDomain(): FilmDomainModel = FilmDomainModel(this.openingCrawl)


fun PlanetDataModel.toDomain(): PlanetDomainModel = PlanetDomainModel(this.name, this.population)


fun SpeciesDataModel.toDomain(): SpeciesDomainModel = SpeciesDomainModel(this.language)

