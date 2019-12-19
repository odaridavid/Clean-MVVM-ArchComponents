package com.k0d4black.theforce.data.source.search

import com.k0d4black.theforce.data.models.entities.SearchedCharacterDataModel


internal interface CharacterSearchDataSource {

    suspend fun query(params: String): List<SearchedCharacterDataModel>

}