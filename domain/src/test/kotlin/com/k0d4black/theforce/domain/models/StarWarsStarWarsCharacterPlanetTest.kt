package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

internal class StarWarsStarWarsCharacterPlanetTest {

    lateinit var expectedModelStarWars: StarWarsCharacterPlanet

    @Test
    fun `instantiate character planet domain model`() {
        //Given
        expectedModelStarWars =
            StarWarsCharacterPlanet(
                name = "Tatooine",
                population = "23444499"
            )
        val actualModel = expectedModelStarWars.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(StarWarsCharacterPlanet::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModelStarWars)
    }
}