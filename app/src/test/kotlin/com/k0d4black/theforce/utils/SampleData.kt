package com.k0d4black.theforce.utils

import com.k0d4black.theforce.domain.models.Character
import com.k0d4black.theforce.domain.models.Film
import com.k0d4black.theforce.domain.models.Planet
import com.k0d4black.theforce.domain.models.Specie

object SampleData {
    val species = listOf(
        Specie(
            name = "name",
            language = "language"
        )
    )
    val films = listOf(
        Film(
            title = "title",
            openingCrawl = "opening crawl"
        )
    )
    val planet =
        Planet(
            name = "name",
            population = "100000"
        )
    val searchResults = listOf(
        Character(
            "Darth Vader",
            "12BBY",
            "123",
            "https://swapi.co/api/species/2/"
        )
    )
}