package com.k0d4black.theforce.domain.usecase


import com.k0d4black.theforce.domain.repository.ICharacterSearchRepository
import com.k0d4black.theforce.domain.usecases.SearchCharactersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class SearchCharactersUseCaseTest {

    @Mock
    private lateinit var searchRepositoryMock: ICharacterSearchRepository

    private lateinit var searchCharactersUseCase: SearchCharactersUseCase

    @Before
    fun setup() {
        searchCharactersUseCase = SearchCharactersUseCase(searchRepositoryMock)
    }

    @Test
    fun `given search parameter when search executed then get search result`() {
        runBlocking {
            //Given
            val searchParameter = "Luke"

            //When
            searchCharactersUseCase(searchParameter)

            //Then
            verify(searchRepositoryMock).searchCharacters(searchParameter)
        }
    }

}