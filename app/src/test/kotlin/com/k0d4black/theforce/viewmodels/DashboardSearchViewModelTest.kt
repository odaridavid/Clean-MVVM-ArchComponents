/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
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
import com.k0d4black.theforce.fakes.FakeSearchCharactersUseCase
import com.k0d4black.theforce.utils.Data
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.utils.observeOnce
import com.k0d4black.theforce.viewmodel.DashboardSearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
@ExperimentalCoroutinesApi
class DashboardSearchViewModelTest : BaseViewModelTest() {

    private lateinit var dashboardSearchViewModel: DashboardSearchViewModel

    @Test
    fun `given a search parameter when search executed then return success state`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            prepareViewModel(UiState.SUCCESS)

            dashboardSearchViewModel.executeCharacterSearch(Data.SEARCH_PARAM)

            advanceTimeBy(600)

            dashboardSearchViewModel.dashboardSearchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.searchResults).isNotEmpty()
            }
        }
    }

    @Test
    fun `given an empty search parameter when search executed then return error state`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            prepareViewModel(UiState.ERROR)

            dashboardSearchViewModel.executeCharacterSearch("")

            advanceTimeBy(600)

            dashboardSearchViewModel.dashboardSearchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }

        }
    }

    override fun prepareViewModel(uiState: UiState) {
        val searchCharactersUseCase = FakeSearchCharactersUseCase(uiState)
        dashboardSearchViewModel = DashboardSearchViewModel(searchCharactersUseCase)
    }

}