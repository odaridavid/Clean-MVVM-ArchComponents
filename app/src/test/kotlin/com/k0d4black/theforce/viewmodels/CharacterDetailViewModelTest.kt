package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.*
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.utils.Data
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.utils.observeOnce
import com.k0d4black.theforce.viewmodel.CharacterDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class CharacterDetailViewModelTest : BaseViewModelTest() {

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @Test
    fun `given a character url when character details request sent then get character details`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            characterDetailViewModel.getCharacterDetails(Data.CHARACTER_URL)

            characterDetailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNull()
                Truth.assertThat(detailViewState.films).isNotEmpty()
                Truth.assertThat(detailViewState.planet).isNotNull()
                Truth.assertThat(detailViewState.specie).isNotEmpty()
            }
        }
    }


    @Test
    fun `given an invalid state when character details request sent then get view state eror`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.ERROR)
            characterDetailViewModel.getCharacterDetails(Data.CHARACTER_URL)
            characterDetailViewModel.detailViewState.observeOnce { detailViewState ->
                Truth.assertThat(detailViewState.error).isNotNull()
            }
        }
    }

    @Test
    fun `given a characters' details when saved and removed from favorites add and remove character`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            characterDetailViewModel.saveFavorite(Data.favorite.toPresentation())

            characterDetailViewModel.detailFavoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
                Truth.assertThat(Data.favorites.size).isEqualTo(1)
            }

            characterDetailViewModel.deleteFavorite(Data.favorite.name)

            characterDetailViewModel.detailFavoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isFalse()
                Truth.assertThat(Data.favorites.size).isEqualTo(0)
            }
        }
    }

    @Test
    fun `given a characters name that exists when db query executed then get character`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            characterDetailViewModel.saveFavorite(Data.favorite.toPresentation())

            characterDetailViewModel.getFavorite(Data.favorite.name)

            characterDetailViewModel.detailFavoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
            }
        }
    }

    override fun prepareViewModel(uiState: UiState) {
        val getFilmsUseCase = FakeGetFilmsUseCase(uiState)
        val getPlanetUseCase = FakeGetPlanetUseCase(uiState)
        val getSpeciesUseCase = FakeGetSpeciesUseCase(uiState)
        val deleteFavoriteByNameUseCase = FakeDeleteFavoriteByNameUseCase(uiState)
        val insertFavoriteUseCase = FakeInsertFavoriteUseCase(uiState)
        val getFavoriteByNameUseCase = FakeGetFavoriteByNameUseCase(uiState)

        characterDetailViewModel =
            CharacterDetailViewModel(
                getSpeciesUseCase,
                getPlanetUseCase,
                getFilmsUseCase,
                deleteFavoriteByNameUseCase,
                insertFavoriteUseCase,
                getFavoriteByNameUseCase
            )
    }

    @After
    fun clear() {
        Data.favorites.clear()
    }

}
