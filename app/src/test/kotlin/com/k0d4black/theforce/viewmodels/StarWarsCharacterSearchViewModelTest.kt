package com.k0d4black.theforce.viewmodels

import com.google.common.truth.Truth
import com.k0d4black.theforce.BaseViewModelTest
import com.k0d4black.theforce.commons.Success
import com.k0d4black.theforce.domain.usecases.SearchStarWarsCharacterUseCase
import com.k0d4black.theforce.features.character_search.CharacterSearchViewModel
import com.k0d4black.theforce.utils.SampleData
import com.k0d4black.theforce.utils.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class StarWarsCharacterSearchViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var searchStarWarsCharacterUseCase: SearchStarWarsCharacterUseCase

    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    private val searchParams = "Darth"

    @Before
    fun setup() {
        characterSearchViewModel = CharacterSearchViewModel(searchStarWarsCharacterUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldReceiveSearchResults() {
        runBlockingTest {
            setMockAnswer()
            characterSearchViewModel.executeCharacterSearch(searchParams)
            characterSearchViewModel.uiState.observeOnce { state ->
                Truth.assertThat(state).isInstanceOf(Success::class.java)
            }
        }
    }

    private suspend fun setMockAnswer() {
        given(searchStarWarsCharacterUseCase(searchParams)).willReturn(flow {
            emit(SampleData.searchResults)
        })
    }
}