package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.PlanetDomainModel
import com.k0d4black.theforce.domain.SearchedCharacterDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class SearchedCharacterDomainModelTest {

    private lateinit var searchedCharacterDomainModel: SearchedCharacterDomainModel

    @Mock
    lateinit var planetDomainModel: PlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val url = "https://swapi.co/api/species/1/"
        val birthYear = "BBY 12"

        searchedCharacterDomainModel =
            SearchedCharacterDomainModel(
                name,
                birthYear,
                url
            )

        //Then
        Truth.assertThat(searchedCharacterDomainModel.name).matches(name)
        Truth.assertThat(searchedCharacterDomainModel.url).matches(url)
    }

}