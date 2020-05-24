package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.commons.Error
import com.k0d4black.theforce.fakes.FakeSearchCharactersUseCase
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
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
            prepareViewModel(UIState.SUCCESS)
            characterSearchViewModel.executeCharacterSearch(searchParams)
            characterSearchViewModel.uiState.observeOnce { state ->
                Truth.assertThat(state).isInstanceOf(Success::class.java)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `given an empty search parameter when search executed then return success state`() {
        runBlockingTest {
            prepareViewModel(UIState.ERROR)
            characterSearchViewModel.executeCharacterSearch(searchParams)
            characterSearchViewModel.uiState.observeOnce { state ->
                Truth.assertThat(state).isInstanceOf(Error::class.java)
            }
        }
    }

    private fun prepareViewModel(uiState: UIState) {
        val searchCharactersUseCase = FakeSearchCharactersUseCase(uiState)
        characterSearchViewModel = CharacterSearchViewModel(searchCharactersUseCase)
    }

    enum class UIState {
        SUCCESS,
        ERROR
    }

}