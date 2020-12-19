/*
*
* Copyright 2020 David Odari
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except 
* in compliance with the License. You may obtain a copy of the License at
*
*          http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed under the License 
* is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
* or implied. See the License for the specific language governing permissions and limitations under
* the License.
*
*/
package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.FakeDeleteFavoriteByNameUseCase
import com.k0d4black.theforce.fakes.FakeGetFavoriteByNameUseCase
import com.k0d4black.theforce.fakes.FakeInsertFavoriteUseCase
import com.k0d4black.theforce.mappers.toPresentation
import com.k0d4black.theforce.utils.Data
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.utils.observeOnce
import com.k0d4black.theforce.viewmodel.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class FavoriteViewModelTest : BaseViewModelTest() {

    // region Members

    private lateinit var favoriteViewModel: FavoriteViewModel

    // endregion

    // region Tests

    @Test
    fun `given a characters' details when saved and removed from favorites add and remove character`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            favoriteViewModel.saveFavorite(Data.favorite.toPresentation())

            favoriteViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
                Truth.assertThat(Data.favorites.size).isEqualTo(1)
            }

            favoriteViewModel.deleteFavorite(Data.favorite.name)

            favoriteViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isFalse()
                Truth.assertThat(Data.favorites.size).isEqualTo(0)
            }
        }
    }


    @Test
    fun `given a characters name that exists when db query executed then get character`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.SUCCESS)

            favoriteViewModel.saveFavorite(Data.favorite.toPresentation())

            favoriteViewModel.getFavorite(Data.favorite.name)

            favoriteViewModel.favoriteViewState.observeOnce { state ->
                Truth.assertThat(state.isFavorite).isTrue()
            }
        }
    }

    // endregion

    // region BaseViewModelTest

    override fun prepareViewModel(uiState: UiState) {
        val deleteFavoriteByNameUseCase = FakeDeleteFavoriteByNameUseCase(uiState)
        val insertFavoriteUseCase = FakeInsertFavoriteUseCase(uiState)
        val getFavoriteByNameUseCase = FakeGetFavoriteByNameUseCase(uiState)
        favoriteViewModel = FavoriteViewModel(
            deleteFavoriteByNameUseCase,
            insertFavoriteUseCase,
            getFavoriteByNameUseCase
        )
    }

    // endregion

    // region Helpers

    @After
    fun clear() {
        Data.favorites.clear()
    }

    // endregion

}