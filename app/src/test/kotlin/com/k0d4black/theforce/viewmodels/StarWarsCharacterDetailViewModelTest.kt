package com.k0d4black.theforce.viewmodels

import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterFilmsUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterPlanetUseCase
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterSpeciesUseCase
import com.k0d4black.theforce.features.character_details.CharacterDetailViewModel
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.utils.SampleData
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class StarWarsCharacterDetailViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var getStarWarsCharacterFilmsUseCase: GetStarWarsCharacterFilmsUseCase
    @Mock
    lateinit var getStarWarsCharacterPlanetUseCase: GetStarWarsCharacterPlanetUseCase
    @Mock
    lateinit var getStarWarsCharacterSpeciesUseCase: GetStarWarsCharacterSpeciesUseCase

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val characterUrl =  "https://swapi.py4e.com/api/people/1/"

    @Before
    fun setup() {
        characterDetailViewModel = CharacterDetailViewModel(
            getStarWarsCharacterSpeciesUseCase,
            getStarWarsCharacterPlanetUseCase,
            getStarWarsCharacterFilmsUseCase
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetCharacterDetails() {
        runBlockingTest {
            setMockAnswers()

            characterDetailViewModel.getCharacterDetails(characterUrl)

            characterDetailViewModel.characterStarWarsCharacterSpecies.observeOnce {
                Truth.assertThat(it)
                    .isEqualTo(SampleData.speciesDomainModel.map { it.toPresentation() })
            }
            characterDetailViewModel.starWarsCharacterFilms.observeOnce {
                Truth.assertThat(it)
                    .isEqualTo(SampleData.characterFilms.map { it.toPresentation() })
            }
            characterDetailViewModel.characterStarWarsCharacterPlanet.observeOnce {
                Truth.assertThat(it).isEqualTo(SampleData.planetDomainModel.toPresentation())
            }
        }
    }

    private suspend fun setMockAnswers() {
        given(getStarWarsCharacterSpeciesUseCase(characterUrl)).willReturn(flow {
            emit(SampleData.speciesDomainModel)
        })
        given(getStarWarsCharacterFilmsUseCase(characterUrl)).willReturn(flow {
            emit(SampleData.characterFilms)
        })
        given(getStarWarsCharacterPlanetUseCase(characterUrl)).willReturn(flow {
            emit(SampleData.planetDomainModel)
        })
    }

}