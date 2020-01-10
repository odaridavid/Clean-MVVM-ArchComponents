package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth.assertThat
import com.k0d4black.theforce.domain.CharacterSpeciesDomainModel
import org.junit.Test

internal class CharacterSpeciesDomainModelTest {

    lateinit var expectedModel: CharacterSpeciesDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        expectedModel = CharacterSpeciesDomainModel(language = "Shyriiwook", name = "Human")
        val actualModel = expectedModel.copy()
        //Then
        assertThat(actualModel).isEqualTo(expectedModel)
    }
}