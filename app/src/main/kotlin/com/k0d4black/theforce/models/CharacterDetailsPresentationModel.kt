package com.k0d4black.theforce.models

data class CharacterDetailsPresentationModel(
    val name: String,
    val birthYear: String,
    val heightInCm: String,
    val heightInInches: String,
    val films: List<FilmPresentationModel?>,
    val species: List<SpeciesPresentationModel?>,
    val homeworld: PlanetPresentationModel
)