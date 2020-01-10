package com.k0d4black.theforce.utils

import com.k0d4black.theforce.domain.models.*

object SampleData {
    val speciesDomainModel = listOf(
        CharacterSpeciesDomainModel(
            name = "name",
            language = "language"
        )
    )
    val characterDomainModel =
        CharacterBasicInfoDomainModel(
            name = "name",
            birthYear = "123 BBY",
            height = "234"
        )
    val characterFilms = listOf(
        CharacterFilmDomainModel(
            title = "title",
            openingCrawl = "opening crawl"
        )
    )
    val planetDomainModel =
        CharacterPlanetDomainModel(
            name = "name",
            population = "100000"
        )
    val searchResults =listOf(
        CharacterSearchDomainModel(
            "Darth Vader",
            "12BBY",
            "https://swapi.co/api/species/2/"
        )
    )
}