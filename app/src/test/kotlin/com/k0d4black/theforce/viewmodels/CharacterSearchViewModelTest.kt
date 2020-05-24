package com.k0d4black.theforce.viewmodels

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.fakes.FakeSearchCharactersUseCase
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
internal class CharacterSearchViewModelTest : BaseViewModelTest() {

    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    private val searchParams = "Darth"

    @Before
    fun setup() {
        val searchCharactersUseCase = FakeSearchCharactersUseCase()
        characterSearchViewModel = CharacterSearchViewModel(searchCharactersUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldReceiveSearchResults() {
        runBlockingTest {
            characterSearchViewModel.executeCharacterSearch(searchParams)
            characterSearchViewModel.uiState.observeOnce { state ->
                Truth.assertThat(state).isInstanceOf(Success::class.java)
            }
        }
    }

}