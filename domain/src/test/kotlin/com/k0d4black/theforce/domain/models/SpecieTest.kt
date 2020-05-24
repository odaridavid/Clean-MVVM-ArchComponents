package com.k0d4black.theforce.domain.models


import com.google.common.truth.Truth
import org.junit.Test

internal class SpecieTest {

    private lateinit var expectedModelStarWars: Specie

    @Test
    fun `instantiate character species domain model`() {
        //Given
        expectedModelStarWars =
            Specie(
                language = "Shyriiwook",
                name = "Human"
            )
        val actualModel = expectedModelStarWars.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(Specie::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModelStarWars)
    }
}