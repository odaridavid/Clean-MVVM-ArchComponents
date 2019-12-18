package com.k0d4black.theforce.data.models.entities


data class CharacterDetailsDataModel(
    var name: String = "",
    var birthYear: String = "",
    var height: String = "",
    var species: List<SpeciesDataModel>? = null,
    var films: List<FilmDataModel>? = null,
    var homeworld: PlanetDataModel? = null
)