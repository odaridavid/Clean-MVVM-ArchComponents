package com.k0d4black.theforce.domain.models

data class SearchedCharacterDomainModel(
    val name: String,
    val species: List<SpeciesDomainModel>,
    val url: String
)

