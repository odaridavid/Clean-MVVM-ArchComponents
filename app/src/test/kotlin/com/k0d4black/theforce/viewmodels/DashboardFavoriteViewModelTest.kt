/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *            http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.FakeDeleteAllFavoritesUseCase
import com.k0d4black.theforce.fakes.FakeDeleteFavoriteByNameUseCase
import com.k0d4black.theforce.fakes.FakeGetAllFavoritesUseCase
import com.k0d4black.theforce.utils.Data
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.feature.favorites.DashboardFavoritesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
internal class DashboardFavoriteViewModelTest : BaseViewModelTest() {

    // region Members

    private lateinit var dashboardFavoritesViewModel: com.k0d4black.theforce.feature.favorites.DashboardFavoritesViewModel

    // endregion

    // region Tests

    @Test
    fun `should get all saved favorites`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            dashboardFavoritesViewModel.favoritesViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isLoading).isFalse()
                Truth.assertThat(state.favorites).isNotNull()
            }
        }
    }

    @Test
    fun `should delete all favorites`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            //Initialize some data
            Data.favorites.add(Data.favorite)

            prepareViewModel(UiState.SUCCESS)

            dashboardFavoritesViewModel.deleteAllFavorites()

            dashboardFavoritesViewModel.favoritesViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isLoading).isFalse()
                Truth.assertThat(state.favorites).isEmpty()
            }
        }
    }

    @Test
    fun `should delete specific favorite`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            //Initialize some data
            Data.favorites.add(Data.favorite)

            prepareViewModel(UiState.SUCCESS)

            dashboardFavoritesViewModel.deleteFavorite(Data.favorite.name)

            dashboardFavoritesViewModel.favoritesViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.isLoading).isFalse()
                Truth.assertThat(state.favorites).isEmpty()
            }
        }
    }

    // endregion

    // region BaseViewModelTest

    override fun prepareViewModel(uiState: UiState) {
        val deleteFavoriteByNameUseCase = FakeDeleteFavoriteByNameUseCase(uiState)
        val getAllFavoritesUseCase = FakeGetAllFavoritesUseCase(uiState)
        val deleteAllFavoritesUseCase = FakeDeleteAllFavoritesUseCase(uiState)

        dashboardFavoritesViewModel =
            com.k0d4black.theforce.feature.favorites.DashboardFavoritesViewModel(
                deleteFavoriteByNameUseCase,
                getAllFavoritesUseCase,
                deleteAllFavoritesUseCase
            )

    }

    // endregion

}