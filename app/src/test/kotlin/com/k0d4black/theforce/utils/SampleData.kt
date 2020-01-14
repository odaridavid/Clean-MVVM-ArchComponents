package com.k0d4black.theforce.utils

import com.k0d4black.theforce.domain.models.StarWarsCharacter
import com.k0d4black.theforce.domain.models.StarWarsCharacterFilm
import com.k0d4black.theforce.domain.models.StarWarsCharacterPlanet
import com.k0d4black.theforce.domain.models.StarWarsCharacterSpecies

object SampleData {
    val speciesDomainModel = listOf(
        StarWarsCharacterSpecies(
            name = "name",
            language = "language"
        )
    )
    val characterFilms = listOf(
        StarWarsCharacterFilm(
            title = "title",
            openingCrawl = "opening crawl"
        )
    )
    val planetDomainModel =
        StarWarsCharacterPlanet(
            name = "name",
            population = "100000"
        )
    val searchResults = listOf(
        StarWarsCharacter(
            "Darth Vader",
            "12BBY",
            "123",
            "https://swapi.co/api/species/2/"
        )
    )
}