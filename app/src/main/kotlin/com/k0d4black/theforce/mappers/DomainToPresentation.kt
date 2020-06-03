package com.k0d4black.theforce.mappers

import com.k0d4black.theforce.commons.convertToInches
import com.k0d4black.theforce.commons.populationToLong
import com.k0d4black.theforce.domain.models.*
import com.k0d4black.theforce.models.*


internal fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        convertToInches(height),
        url
    )
}

internal fun Planet.toPresentation(): PlanetPresentation {
    return PlanetPresentation(name, populationToLong(population))
}

internal fun Film.toPresentation(): FilmPresentation {
    return FilmPresentation(title, openingCrawl)
}

internal fun Specie.toPresentation(): SpeciePresentation {
    return SpeciePresentation(name, language)
}

internal fun Favorite.toPresentation(): FavoritePresentation {
    return FavoritePresentation(
        id = id,
        name = name,
        birthYear = birthYear,
        height = height,
        heightInInches = convertToInches(height),
        planetName = planetName,
        planetPopulation = populationToLong(planetPopulation),
        specieName = specieName,
        specieLanguage = specieLanguage,
        films = films.map { it.toPresentation() }
    )
}