package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.fakes.FakeSearchCharactersUseCase
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import com.k0d4black.theforce.utils.UiState
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class CharacterSearchViewModelTest : BaseViewModelTest() {

    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    private val searchParams = "Darth"

    @ExperimentalCoroutinesApi
    @Test
    fun `given a search parameter when search executed then return success state`() {
        runBlockingTest {
            prepareViewModel(UiState.SUCCESS)
            characterSearchViewModel.executeCharacterSearch(searchParams)
            characterSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNull()
                Truth.assertThat(state.searchResults).isNotEmpty()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given an empty search parameter when search executed then return error state`() {
        runBlockingTest {
            prepareViewModel(UiState.ERROR)
            characterSearchViewModel.executeCharacterSearch("")
            characterSearchViewModel.searchViewState.observeOnce { state ->
                Truth.assertThat(state.error).isNotNull()
            }
        }
    }

    override fun prepareViewModel(uiState: UiState) {
        val searchCharactersUseCase = FakeSearchCharactersUseCase(uiState)
        characterSearchViewModel = CharacterSearchViewModel(searchCharactersUseCase)
    }
}