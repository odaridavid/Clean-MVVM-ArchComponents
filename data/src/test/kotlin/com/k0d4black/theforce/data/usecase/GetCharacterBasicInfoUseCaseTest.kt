package com.k0d4black.theforce.data.usecase

import com.k0d4black.theforce.data.repository.CharacterDetailsRepository
import com.k0d4black.theforce.data.usecases.GetCharacterBasicInfoUseCase

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetCharacterBasicInfoUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: CharacterDetailsRepository

    private lateinit var getCharacterBasicInfoUseCase: GetCharacterBasicInfoUseCase

    @Before
    fun setup() {
        getCharacterBasicInfoUseCase = GetCharacterBasicInfoUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = 10

            //When
            getCharacterBasicInfoUseCase.execute(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterDetails(characterId)
        }
    }
}