/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
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