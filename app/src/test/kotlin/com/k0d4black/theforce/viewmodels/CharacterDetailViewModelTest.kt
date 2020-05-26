package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.FakeGetFilmsUseCase
import com.k0d4black.theforce.fakes.FakeGetPlanetUseCase
import com.k0d4black.theforce.fakes.FakeGetSpeciesUseCase
import com.k0d4black.theforce.features.character_details.CharacterDetailViewModel
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class CharacterDetailViewModelTest : BaseViewModelTest() {

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    private val characterUrl = "/api/people/1/"

    @ExperimentalCoroutinesApi
    @Test
    fun `given a character url when character details request sent then get character details`() {
        runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            characterDetailViewModel.getCharacterDetails(characterUrl)
            characterDetailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNull()
                Truth.assertThat(detailViewState.isLoading).isFalse()
                Truth.assertThat(detailViewState.films).isNotEmpty()
                Truth.assertThat(detailViewState.planet).isNotNull()
                Truth.assertThat(detailViewState.specie).isNotEmpty()
            }
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `given an invalid state when character details request sent then get view state eror`() {
        runBlockingTest {
            prepareViewModel(UiState.ERROR)
            characterDetailViewModel.getCharacterDetails(characterUrl)
            characterDetailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNotNull()
                Truth.assertThat(detailViewState.isLoading).isFalse()
            }
        }
    }

    override fun prepareViewModel(uiState: UiState) {
        val getFilmsUseCase = FakeGetFilmsUseCase(uiState)
        val getPlanetUseCase = FakeGetPlanetUseCase(uiState)
        val getSpeciesUseCase = FakeGetSpeciesUseCase(uiState)
        characterDetailViewModel = CharacterDetailViewModel(
            getSpeciesUseCase,
            getPlanetUseCase,
            getFilmsUseCase
        )
    }

}