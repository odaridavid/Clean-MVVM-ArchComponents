package com.k0d4black.theforce.utils

import com.k0d4black.theforce.domain.*

object SampleData {
    val speciesDomainModel = listOf(SpeciesDomainModel(name = "name", language = "language"))
    val characterDomainModel = CharacterDetailsDomainModel(
        name = "name",
        birthYear = "123 BBY",
        height = "234"
    )
    val characterFilms = listOf(FilmDomainModel(title = "title", openingCrawl = "opening crawl"))
    val planetDomainModel = PlanetDomainModel(name = "name", population = "100000")
    val searchResults =listOf(SearchedCharacterDomainModel("Darth Vader", "12BBY", "https://swapi.co/api/species/2/"))
}