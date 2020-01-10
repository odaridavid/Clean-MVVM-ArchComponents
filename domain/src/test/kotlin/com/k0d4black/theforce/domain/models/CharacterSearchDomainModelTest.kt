package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterSearchDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
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