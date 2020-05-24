package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetFilmsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetFilmsUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

    private lateinit var getFilmsUseCase: GetFilmsUseCase

    @Before
    fun setup() {
        getFilmsUseCase = GetFilmsUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `given character url when request for film then get character film`() {
        runBlocking {
            //Given
            val characterUrl = "/api/people/3/"

            //When
            getFilmsUseCase(characterUrl)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterFilms(characterUrl)
        }
    }
}