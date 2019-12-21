package com.k0d4black.theforce.data.usecase

import com.k0d4black.theforce.data.repository.CharacterSearchRepository
import com.k0d4black.theforce.data.usecases.CharacterSearchUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CharacterSearchUseCaseTest {

    @Mock
    private lateinit var searchRepositoryMock: CharacterSearchRepository

    private lateinit var characterSearchUseCase: CharacterSearchUseCase

    @Before
    fun setup() {
        characterSearchUseCase = CharacterSearchUseCase(searchRepositoryMock)
    }

    @Test
    fun `when search executed then get search result`() {
        runBlocking {
            //Given
            val searchParameter = "Luke"

            //When
            characterSearchUseCase.searchCharacters(searchParameter)

            //Then
            verify(searchRepositoryMock).searchCharacters(searchParameter)
        }
    }

}