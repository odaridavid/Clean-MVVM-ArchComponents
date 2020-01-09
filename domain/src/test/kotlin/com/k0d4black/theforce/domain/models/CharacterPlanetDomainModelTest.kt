package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterPlanetDomainModel
import org.junit.Test

internal class CharacterPlanetDomainModelTest {

    lateinit var characterPlanetDomainModel: CharacterPlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Tatooine"
        val population = "23444499"

        characterPlanetDomainModel =
            CharacterPlanetDomainModel(
                name,
                population
            )

        //Then
        Truth.assertThat(characterPlanetDomainModel.name).matches(name)
        Truth.assertThat(characterPlanetDomainModel.population).matches(population)
    }
}