package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.FakeGetFilmsUseCase
import com.k0d4black.theforce.fakes.FakeGetPlanetUseCase
import com.k0d4black.theforce.fakes.FakeGetSpeciesUseCase
import com.k0d4black.theforce.features.character_details.CharacterDetailViewModel
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.utils.SampleData
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class CharacterDetailViewModelTest : BaseViewModelTest() {

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val characterUrl = "/api/people/1/"

    @Before
    fun setup() {
        val getFilmsUseCase = FakeGetFilmsUseCase()
        val getPlanetUseCase = FakeGetPlanetUseCase()
        val getSpeciesUseCase = FakeGetSpeciesUseCase()
        characterDetailViewModel = CharacterDetailViewModel(
            getSpeciesUseCase,
            getPlanetUseCase,
            getFilmsUseCase
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given a character url when character details request sent then get character details`() {
        runBlockingTest {
            characterDetailViewModel.getCharacterDetails(characterUrl)

            characterDetailViewModel.species.observeOnce { speciesPresentation ->
                Truth.assertThat(speciesPresentation)
                    .isEqualTo(SampleData.species.map { it.toPresentation() })
            }
            characterDetailViewModel.films.observeOnce { filmPresentation ->
                Truth.assertThat(filmPresentation)
                    .isEqualTo(SampleData.films.toPresentation())
            }
            characterDetailViewModel.planet.observeOnce { planetPresentation ->
                Truth.assertThat(planetPresentation).isEqualTo(SampleData.planet.toPresentation())
            }
        }
    }

}