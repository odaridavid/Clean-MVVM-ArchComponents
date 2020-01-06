package com.k0d4black.theforce.data.usecase

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.usecases.CharacterDetailsUseCase

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CharacterDetailsUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: CharacterDetailsRepository

    private lateinit var characterDetailsUseCase: CharacterDetailsUseCase

    @Before
    fun setup() {
        characterDetailsUseCase = CharacterDetailsUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = 10

            //When
            characterDetailsUseCase.getCharacterBasicDetails(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterDetails(characterId)
        }
    }
}