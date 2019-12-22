package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth.assertThat
import com.k0d4black.theforce.domain.SpeciesDomainModel
import org.junit.Test

internal class SpeciesDomainModelTest {

    lateinit var speciesDomainModel: SpeciesDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val language = "Shyriiwook"
        val name = "Human"

        speciesDomainModel =
            SpeciesDomainModel(name, language)

        //Then
        assertThat(speciesDomainModel.language).contains(language)
    }
}