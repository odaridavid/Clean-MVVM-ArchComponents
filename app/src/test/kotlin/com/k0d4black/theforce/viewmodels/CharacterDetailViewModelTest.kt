package com.k0d4black.theforce.viewmodels

import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.data.usecases.CharacterDetailsUseCase
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
internal class CharacterDetailViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var characterDetailsUseCase: CharacterDetailsUseCase

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val characterIdParams = 1

    @Before
    fun setup() {
        characterDetailViewModel = CharacterDetailViewModel(characterDetailsUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetCharacterDetails() {
        runBlockingTest {
            setMockAnswers()

            characterDetailViewModel.getCharacterDetails(characterIdParams)

            characterDetailViewModel.characterDetail.observeOnce {
                Truth.assertThat(it).isEqualTo(SampleData.characterDomainModel.toPresentation())
            }
            characterDetailViewModel.characterSpecies.observeOnce {
                Truth.assertThat(it)
                    .isEqualTo(SampleData.speciesDomainModel.map { it.toPresentation() })
            }
            characterDetailViewModel.characterFilms.observeOnce {
                Truth.assertThat(it)
                    .isEqualTo(SampleData.characterFilms.map { it.toPresentation() })
            }
            characterDetailViewModel.characterPlanet.observeOnce {
                Truth.assertThat(it).isEqualTo(SampleData.planetDomainModel.toPresentation())
            }
        }


    }

    private suspend fun setMockAnswers() {
        given(characterDetailsUseCase.getCharacterSpecies(characterIdParams)).willReturn(flow {
            emit(SampleData.speciesDomainModel)
        })
        given(characterDetailsUseCase.getCharacterBasicDetails(characterIdParams)).willReturn(flow {
            emit(SampleData.characterDomainModel)
        })
        given(characterDetailsUseCase.getCharacterFilms(characterIdParams)).willReturn(flow {
            emit(SampleData.characterFilms)
        })
        given(characterDetailsUseCase.getCharacterPlanet(characterIdParams)).willReturn(flow {
            emit(SampleData.planetDomainModel)
        })
    }

}