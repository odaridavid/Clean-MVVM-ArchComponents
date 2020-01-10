package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test


internal class CharacterBasicInfoDomainModelTest {

    lateinit var expectedModel: CharacterBasicInfoDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        expectedModel =
            CharacterBasicInfoDomainModel(
                name = "Luke Skywalker",
                birthYear = "19 BBY",
                height = "172"
            )
        val actualModel = expectedModel.copy()
        //Then
        Truth.assertThat(actualModel).isEqualTo(expectedModel)
    }
}
