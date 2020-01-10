package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class CharacterPlanetDomainModelTest {

    lateinit var expectedModel: CharacterPlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        expectedModel =
            CharacterPlanetDomainModel(
                name = "Tatooine",
                population = "23444499"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }
}