package com.k0d4black.theforce.data.models.entities


data class SearchedCharacterDataModel(
    var name: String = "",
    var species: List<SpeciesDataModel>? = null,
    var url: String = ""
)