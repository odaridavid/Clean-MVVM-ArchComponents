package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entity.StarWarsCharacterFilmEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterPlanetEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterSpeciesEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterEntity
import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies


internal fun StarWarsCharacterEntity.toDomain(): StarWarsCharacter {
    return StarWarsCharacter(
        this.name,
        this.birthYear,
        this.height,
        this.url
    )
}

internal fun StarWarsCharacterFilmEntity.toDomain(): StarWarsCharacterFilm =
    StarWarsCharacterFilm(
        this.title,
        this.openingCrawl
    )


internal fun StarWarsCharacterPlanetEntity.toDomain(): StarWarsCharacterPlanet =
    StarWarsCharacterPlanet(
        this.name,
        this.population
    )


internal fun StarWarsCharacterSpeciesEntity.toDomain(): StarWarsCharacterSpecies =
    StarWarsCharacterSpecies(
        this.name,
        this.language
    )

