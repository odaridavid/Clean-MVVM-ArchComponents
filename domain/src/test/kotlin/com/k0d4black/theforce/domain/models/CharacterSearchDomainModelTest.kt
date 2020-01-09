package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import com.k0d4black.theforce.domain.CharacterPlanetDomainModel
import com.k0d4black.theforce.domain.CharacterSearchDomainModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CharacterSearchDomainModelTest {

    private lateinit var characterSearchDomainModel: CharacterSearchDomainModel

    @Mock
    lateinit var characterPlanetDomainModel: CharacterPlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val url = "https://swapi.co/api/species/1/"
        val birthYear = "BBY 12"

        characterSearchDomainModel =
            CharacterSearchDomainModel(
                name,
                birthYear,
                url
            )

        //Then
        Truth.assertThat(characterSearchDomainModel.name).matches(name)
        Truth.assertThat(characterSearchDomainModel.url).matches(url)
    }

}