package com.k0d4black.theforce.data.models

data class CharacterDetailsDataModel(
    var name: String,
    var birthYear: String,
    var height: String,
    var species: List<SpeciesDataModel>?,
    var films: List<FilmDataModel>?,
    var homeworld: PlanetDataModel?
)