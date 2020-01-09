package com.k0d4black.theforce.data.usecase

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.usecases.GetCharacterBasicInfoUseCase
import com.k0d4black.theforce.data.usecases.GetCharacterPlanetUseCase

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetCharacterPlanetUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: CharacterDetailsRepository

    private lateinit var getCharacterPlanetUseCase: GetCharacterPlanetUseCase

    @Before
    fun setup() {
        getCharacterPlanetUseCase = GetCharacterPlanetUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = 10

            //When
            getCharacterPlanetUseCase.execute(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterPlanet(characterId)
        }
    }
}