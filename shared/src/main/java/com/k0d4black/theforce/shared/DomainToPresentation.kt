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

import com.k0d4black.theforce.shared.android.convertToInches
import com.k0d4black.theforce.shared.android.populationToLong
import com.k0d4black.theforce.domain.models.*
import com.k0d4black.theforce.models.*


internal fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        com.k0d4black.theforce.shared.android.convertToInches(height),
        url
    )
}

internal fun Planet.toPresentation(): com.k0d4black.theforce.feature.character.details.PlanetPresentation {
    return com.k0d4black.theforce.feature.character.details.PlanetPresentation(
        name,
        com.k0d4black.theforce.shared.android.populationToLong(population)
    )
}

internal fun Film.toPresentation(): com.k0d4black.theforce.feature.character.details.FilmPresentation {
    return com.k0d4black.theforce.feature.character.details.FilmPresentation(title, openingCrawl)
}

internal fun Specie.toPresentation(): com.k0d4black.theforce.feature.character.details.SpeciePresentation {
    return com.k0d4black.theforce.feature.character.details.SpeciePresentation(name, language)
}

internal fun Favorite.toPresentation(): com.k0d4black.theforce.feature.character.details.FavoritePresentation {
    val characterPresentation =
        CharacterPresentation(name, birthYear, height,
            com.k0d4black.theforce.shared.android.convertToInches(height), "")
    val planetPresentation = com.k0d4black.theforce.feature.character.details.PlanetPresentation(
        planetName,
        com.k0d4black.theforce.shared.android.populationToLong(planetPopulation)
    )
    val speciePresentation = com.k0d4black.theforce.feature.character.details.SpeciePresentation(
        specieName,
        specieLanguage
    )
    return com.k0d4black.theforce.feature.character.details.FavoritePresentation(
        characterPresentation = characterPresentation,
        planetPresentation = planetPresentation,
        speciePresentation = listOf(speciePresentation),
        films = films.map { it.toPresentation() }
    )
}