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
    val characterPresentation =
        CharacterPresentation(name, birthYear, height, convertToInches(height), "")
    val planetPresentation = PlanetPresentation(planetName, populationToLong(planetPopulation))
    val speciePresentation = SpeciePresentation(specieName, specieLanguage)
    return FavoritePresentation(
        characterPresentation = characterPresentation,
        planetPresentation = planetPresentation,
        speciePresentation = speciePresentation,
        films = films.map { it.toPresentation() }
    )
}