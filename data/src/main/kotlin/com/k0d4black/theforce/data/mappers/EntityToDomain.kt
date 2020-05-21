package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entity.FilmEntity
import com.k0d4black.theforce.data.models.entity.PlanetEntity
import com.k0d4black.theforce.data.models.entity.SpecieEntity
import com.k0d4black.theforce.data.models.entity.CharacterEntity
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie


internal fun CharacterEntity.toDomain(): Character {
    return Character(
        name,
        birthYear,
        height,
        url
    )
}

internal fun FilmEntity.toDomain(): Film =
    Film(
        this.title,
        this.openingCrawl
    )


internal fun PlanetEntity.toDomain(): Planet =
    Planet(
        this.name,
        this.population
    )


internal fun SpecieEntity.toDomain(): Specie =
    Specie(
        this.name,
        this.language
    )

