package com.k0d4black.theforce.data.remote.mappers


import com.k0d4black.theforce.data.remote.models.CharacterResponse
import com.k0d4black.theforce.data.remote.models.FilmDetailResponse
import com.k0d4black.theforce.data.remote.models.PlanetDetailsResponse
import com.k0d4black.theforce.data.remote.models.SpecieDetailResponse
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie

internal fun CharacterResponse.toDomain(): Character {
    return Character(this.name, this.birthYear, this.height, this.url)
}

internal fun PlanetDetailsResponse.toDomain(): Planet {
    return Planet(this.name, this.population)
}

internal fun SpecieDetailResponse.toDomain(): Specie {
    return Specie(this.name, this.language)
}

internal fun FilmDetailResponse.toDomain(): Film {
    return Film(this.title, this.openingCrawl)
}