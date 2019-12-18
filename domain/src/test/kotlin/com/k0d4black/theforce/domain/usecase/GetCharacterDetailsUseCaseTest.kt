package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.CharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetCharacterDetailsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharacterDetailsUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: CharacterDetailsRepository

    private lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase

    @Before
    fun setup() {
        getCharacterDetailsUseCase = GetCharacterDetailsUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = 10

            //When
            getCharacterDetailsUseCase.getCharacterDetails(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterDetails(characterId)
        }
    }
}