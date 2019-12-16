package com.k0d4black.theforce.domain

/**
 * Created By David Odari
 * On 16/12/19
 *
 **/
data class CharacterDetails(
    val name: String,
    val birthYear: String,
    val height: String,
    val species: Species,
    val films: List<Film>
)

data class SearchedCharacter(
    val name: String,
    val species: Species
)
