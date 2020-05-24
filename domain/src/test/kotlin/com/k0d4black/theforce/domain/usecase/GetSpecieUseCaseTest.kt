package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetSpeciesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetSpecieUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

    private lateinit var getSpeciesUseCase: GetSpeciesUseCase

    @Before
    fun setup() {
        getSpeciesUseCase = GetSpeciesUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `given character url when request for species then get character species`() {
        runBlocking {
            //Given
            val characterUrl = "/api/people/3/"

            //When
            getSpeciesUseCase(characterUrl)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterSpecies(characterUrl)
        }
    }
}