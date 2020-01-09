package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth.assertThat
import com.k0d4black.theforce.domain.CharacterSpeciesDomainModel
import org.junit.Test

internal class CharacterSpeciesDomainModelTest {

    lateinit var characterSpeciesDomainModel: CharacterSpeciesDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val language = "Shyriiwook"
        val name = "Human"

        characterSpeciesDomainModel =
            CharacterSpeciesDomainModel(name, language)

        //Then
        assertThat(characterSpeciesDomainModel.language).contains(language)
    }
}