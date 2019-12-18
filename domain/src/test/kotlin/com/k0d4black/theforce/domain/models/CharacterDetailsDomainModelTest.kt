package com.k0d4black.theforce.domain.models

import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsDomainModelTest {

    lateinit var characterDetailsDomainModel: CharacterDetailsDomainModel

    //Collaborators
    @Mock
    lateinit var speciesDomainModel: SpeciesDomainModel
    @Mock
    lateinit var filmDomainModel: FilmDomainModel
    @Mock
    lateinit var planetDomainModel: PlanetDomainModel

    @Test
    fun `model instantiated has given values`() {
        //Given
        val name = "Luke Skywalker"
        val birthYear = "19 BBY"
        val height = "172"
        val listOfSpecies = mutableListOf(speciesDomainModel)
        val listOfFilms = mutableListOf(filmDomainModel)

        characterDetailsDomainModel =
            CharacterDetailsDomainModel(
                name,
                birthYear,
                height,
                listOfSpecies,
                listOfFilms,
                planetDomainModel
            )

        //Then
        Truth.assertThat(characterDetailsDomainModel.name).matches(name)
        Truth.assertThat(characterDetailsDomainModel.birthYear).matches(birthYear)
        Truth.assertThat(characterDetailsDomainModel.height).matches(height)
        Truth.assertThat(characterDetailsDomainModel.species).contains(speciesDomainModel)
        Truth.assertThat(characterDetailsDomainModel.films).contains(filmDomainModel)
        Truth.assertThat(characterDetailsDomainModel.homeworld)
            .isInstanceOf(PlanetDomainModel::class.java)
    }
}