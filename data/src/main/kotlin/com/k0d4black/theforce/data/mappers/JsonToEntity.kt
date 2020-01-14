package com.k0d4black.theforce.data.mappers

import com.k0d4black.theforce.data.models.entity.StarWarsCharacterEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterFilmEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterPlanetEntity
import com.k0d4black.theforce.data.models.entity.StarWarsCharacterSpeciesEntity
import com.k0d4black.theforce.data.models.response.CharacterResponse
import com.k0d4black.theforce.data.models.response.details.FilmResponse
import com.k0d4black.theforce.data.models.response.details.PlanetResponse
import com.k0d4black.theforce.data.models.response.details.SpeciesResponse

internal fun CharacterResponse.toEntity(): StarWarsCharacterEntity {
    return StarWarsCharacterEntity(this.name, this.birthYear, this.height, this.url)
}

internal fun PlanetResponse.toEntity(): StarWarsCharacterPlanetEntity {
    return StarWarsCharacterPlanetEntity(this.name, this.population)
}

internal fun SpeciesResponse.toEntity(): StarWarsCharacterSpeciesEntity {
    return StarWarsCharacterSpeciesEntity(this.name, this.language)
}

internal fun FilmResponse.toEntity(): StarWarsCharacterFilmEntity {
    return StarWarsCharacterFilmEntity(this.title, this.openingCrawl)
}