package com.k0d4black.theforce.domain.models


data class CharacterDetailsDomainModel(
    val name: String,
    val birthYear: String,
    val height: String,
    val species: List<SpeciesDomainModel>,
    val films: List<FilmDomainModel>,
    val homeworld: PlanetDomainModel?
)
