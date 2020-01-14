package com.k0d4black.theforce.data.models.response.details

data class CharacterDetailsSpeciesResponse(val species: List<String>)

/**
 * Object Representation of string response
 */
data class SpeciesResponse(
    val name: String,
    val language: String
)