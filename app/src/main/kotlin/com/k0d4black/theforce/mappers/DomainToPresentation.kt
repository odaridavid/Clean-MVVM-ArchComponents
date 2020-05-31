package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.commons.convertToInches
import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie
import com.k0d4black.theforce.models.CharacterPresentation
import com.k0d4black.theforce.models.FilmPresentation
import com.k0d4black.theforce.models.PlanetPresentation
import com.k0d4black.theforce.models.SpeciePresentation


fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        this.name,
        this.birthYear,
        this.height,
        convertToInches(this.height),
        this.url
    )
}

fun Planet.toPresentation(): PlanetPresentation {
    val populationAsLong = if (this.population.contains("Unknown")) 0L else this.population.toLong()
    return PlanetPresentation(this.name, populationAsLong)
}

fun Film.toPresentation(): FilmPresentation {
    return FilmPresentation(this.title, this.openingCrawl)
}

fun Specie.toPresentation(): SpeciePresentation {
    return SpeciePresentation(this.name, this.language)
}