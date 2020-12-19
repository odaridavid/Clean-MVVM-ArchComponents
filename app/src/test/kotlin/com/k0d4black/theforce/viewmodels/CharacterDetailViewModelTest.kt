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

    // region Members

    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    // endregion

    // region Tests

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

    // endregion

    // region BaseViewModelTest

    override fun prepareViewModel(uiState: UiState) {
        val getFilmsUseCase = FakeGetFilmsUseCase(uiState)
        val getPlanetUseCase = FakeGetPlanetUseCase(uiState)
        val getSpeciesUseCase = FakeGetSpeciesUseCase(uiState)

        characterDetailViewModel =
            CharacterDetailViewModel(
                getSpeciesUseCase,
                getPlanetUseCase,
                getFilmsUseCase
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
