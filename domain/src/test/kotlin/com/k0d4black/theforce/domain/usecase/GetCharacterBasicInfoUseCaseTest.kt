package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetCharacterBasicInfoUseCase

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
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

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