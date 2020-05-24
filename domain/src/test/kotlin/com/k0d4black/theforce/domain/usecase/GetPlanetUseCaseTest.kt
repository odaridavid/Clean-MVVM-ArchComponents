package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetPlanetUseCase

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetPlanetUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

    private lateinit var getPlanetUseCase: GetPlanetUseCase

    @Before
    fun setup() {
        getPlanetUseCase = GetPlanetUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `given character url when request for planet then get character planet`() {
        runBlocking {
            //Given
            val characterUrl = "/api/people/3/"

            //When
            getPlanetUseCase(characterUrl)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterPlanet(characterUrl)
        }
    }
}