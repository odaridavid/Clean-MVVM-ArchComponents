package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterPlanetUseCase

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetStarWarsCharacterPlanetUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

    private lateinit var getStarWarsCharacterPlanetUseCase: GetStarWarsCharacterPlanetUseCase

    @Before
    fun setup() {
        getStarWarsCharacterPlanetUseCase = GetStarWarsCharacterPlanetUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = 10

            //When
            getStarWarsCharacterPlanetUseCase.execute(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterPlanet(characterId)
        }
    }
}