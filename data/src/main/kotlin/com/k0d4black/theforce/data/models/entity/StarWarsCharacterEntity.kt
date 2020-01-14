package com.k0d4black.theforce.data.models.entity

/**
 * Provides a shallow character model with minimal data
 */
data class StarWarsCharacterEntity(
    var name: String,
    var birthYear: String,
    var height: String,
    var url: String
)