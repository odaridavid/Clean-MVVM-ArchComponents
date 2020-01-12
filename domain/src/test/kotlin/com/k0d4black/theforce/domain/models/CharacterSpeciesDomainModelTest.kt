package com.k0d4black.theforce.domain.models


import com.google.common.truth.Truth
import org.junit.Test

internal class CharacterSpeciesDomainModelTest {

    lateinit var expectedModel: CharacterSpeciesDomainModel

    @Test
    fun `instantiate character species domain model`() {
        //Given
        expectedModel =
            CharacterSpeciesDomainModel(
                language = "Shyriiwook",
                name = "Human"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isInstanceOf(CharacterSpeciesDomainModel::class.java)
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }
}