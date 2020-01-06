package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterDetailsDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CharacterDetailsDomainModelTest {

    lateinit var characterDetailsDomainModel: CharacterDetailsDomainModel


    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val birthYear = "19 BBY"
        val height = "172"
       

        characterDetailsDomainModel =
            CharacterDetailsDomainModel(
                name,
                birthYear,
                height
            )

        //Then
        Truth.assertThat(characterDetailsDomainModel.name).matches(name)
        Truth.assertThat(characterDetailsDomainModel.birthYear).matches(birthYear)
        Truth.assertThat(characterDetailsDomainModel.height).matches(height)
    }
}
