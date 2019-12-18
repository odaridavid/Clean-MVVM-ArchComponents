package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test

class PlanetDomainModelTest {

    lateinit var planetDomainModel: PlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Tatooine"
        val population = "23444499"

        planetDomainModel = PlanetDomainModel(name, population)

        //Then
        Truth.assertThat(planetDomainModel.name).matches(name)
        Truth.assertThat(planetDomainModel.population).matches(population)
    }
}