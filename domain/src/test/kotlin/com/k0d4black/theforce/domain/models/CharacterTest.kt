package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test


internal class CharacterTest {

    private lateinit var expectedModel: Character

    @Test
    fun `instantiate character basic info domain model`() {
        //Given
        expectedModel =
            Character(
                name = "Luke Skywalker",
                birthYear = "19 BBY",
                height = "172",
                url = "www.wkwk.ckck/23"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(Character::class.java)
        Truth.assertThat(expectedModel).isInstanceOf(Character::class.java)
    }
}
