package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class CharacterPlanetDomainModelTest {

    lateinit var expectedModel: CharacterPlanetDomainModel

    @Test
    fun `instantiate character planet domain model`() {
        //Given
        expectedModel =
            CharacterPlanetDomainModel(
                name = "Tatooine",
                population = "23444499"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(CharacterPlanetDomainModel::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }
}