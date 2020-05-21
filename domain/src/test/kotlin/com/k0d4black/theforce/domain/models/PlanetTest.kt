package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class PlanetTest {

    lateinit var expectedModelStarWars: Planet

    @Test
    fun `instantiate character planet domain model`() {
        //Given
        expectedModelStarWars =
            Planet(
                name = "Tatooine",
                population = "23444499"
            )
        val actualModel = expectedModelStarWars.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(Planet::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModelStarWars)
    }
}