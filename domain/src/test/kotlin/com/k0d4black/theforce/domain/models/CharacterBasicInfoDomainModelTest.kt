package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterBasicInfoDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CharacterBasicInfoDomainModelTest {

    lateinit var characterBasicInfoDomainModel: CharacterBasicInfoDomainModel


    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val birthYear = "19 BBY"
        val height = "172"
       

        characterBasicInfoDomainModel =
            CharacterBasicInfoDomainModel(
                name,
                birthYear,
                height
            )

        //Then
        Truth.assertThat(characterBasicInfoDomainModel.name).matches(name)
        Truth.assertThat(characterBasicInfoDomainModel.birthYear).matches(birthYear)
        Truth.assertThat(characterBasicInfoDomainModel.height).matches(height)
    }
}
