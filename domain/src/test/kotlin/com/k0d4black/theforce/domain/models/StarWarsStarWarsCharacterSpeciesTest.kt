package com.k0d4black.theforce.domain.models


import com.google.common.truth.Truth
import org.junit.Test

internal class StarWarsStarWarsCharacterSpeciesTest {

    lateinit var expectedModelStarWars: StarWarsCharacterSpecies

    @Test
    fun `instantiate character species domain model`() {
        //Given
        expectedModelStarWars =
            StarWarsCharacterSpecies(
                language = "Shyriiwook",
                name = "Human"
            )
        val actualModel = expectedModelStarWars.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(StarWarsCharacterSpecies::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModelStarWars)
    }
}