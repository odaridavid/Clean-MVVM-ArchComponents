package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entity.CharacterEntity
import com.k0d4black.theforce.data.models.entity.FilmEntity
import com.k0d4black.theforce.data.models.entity.PlanetEntity
import com.k0d4black.theforce.data.models.entity.SpecieEntity
import com.k0d4black.theforce.data.models.response.CharacterResponse
import com.k0d4black.theforce.data.models.response.FilmDetailResponse
import com.k0d4black.theforce.data.models.response.PlanetDetailsResponse
import com.k0d4black.theforce.data.models.response.SpecieDetailResponse

internal fun CharacterResponse.toEntity(): CharacterEntity {
    return CharacterEntity(this.name, this.birthYear, this.height, this.url)
}

internal fun PlanetDetailsResponse.toEntity(): PlanetEntity {
    return PlanetEntity(this.name, this.population)
}

internal fun SpecieDetailResponse.toEntity(): SpecieEntity {
    return SpecieEntity(this.name, this.language)
}

internal fun FilmDetailResponse.toEntity(): FilmEntity {
    return FilmEntity(this.title, this.openingCrawl)
}