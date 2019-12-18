package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchedCharacterDomainModelTest {

    lateinit var searchedCharacterDomainModel: SearchedCharacterDomainModel

    @Mock
    lateinit var speciesDomainModel: SpeciesDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val url = "https://swapi.co/api/species/1/"
        val species = mutableListOf(speciesDomainModel)

        searchedCharacterDomainModel = SearchedCharacterDomainModel(name, species, url)

        //Then
        Truth.assertThat(searchedCharacterDomainModel.name).matches(name)
        Truth.assertThat(searchedCharacterDomainModel.url).matches(url)
        Truth.assertThat(searchedCharacterDomainModel.species).contains(speciesDomainModel)
    }

}