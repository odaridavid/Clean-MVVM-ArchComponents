package com.k0d4black.theforce.data.models.entities


internal data class SearchedCharacterDataModel(
    var name: String = "",
    var species: List<SpeciesDataModel>? = null,
    var url: String = ""
)