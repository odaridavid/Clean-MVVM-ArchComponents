package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test


internal class CharacterSearchDomainModelTest {

    private lateinit var expectedModel: CharacterSearchDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        expectedModel =
            CharacterSearchDomainModel(
                name = "Luke Skywalker",
                url = "https://swapi.co/api/species/1/",
                birthYear = "BBY 12"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }

}