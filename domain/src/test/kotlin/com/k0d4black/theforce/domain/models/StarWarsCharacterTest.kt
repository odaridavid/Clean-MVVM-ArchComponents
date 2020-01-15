package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test


internal class StarWarsCharacterTest {

    lateinit var expectedModel: StarWarsCharacter

    @Test
    fun `instantiate character basic info domain model`() {
        //Given
        expectedModel =
            StarWarsCharacter(
                name = "Luke Skywalker",
                birthYear = "19 BBY",
                height = "172",
                url ="www.wkwk.ckck/23"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(StarWarsCharacter::class.java)
        Truth.assertThat(expectedModel).isInstanceOf(StarWarsCharacter::class.java)
    }
}
