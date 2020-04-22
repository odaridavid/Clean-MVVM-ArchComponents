package com.k0d4black.theforce.domain.usecase

import com.k0d4black.theforce.domain.repository.ICharacterDetailsRepository
import com.k0d4black.theforce.domain.usecases.GetStarWarsCharacterSpeciesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class GetStarWarsCharacterSpeciesUseCaseTest {
    @Mock
    private lateinit var characterDetailsRepositoryMock: ICharacterDetailsRepository

    private lateinit var getStarWarsCharacterSpeciesUseCase: GetStarWarsCharacterSpeciesUseCase

    @Before
    fun setup() {
        getStarWarsCharacterSpeciesUseCase = GetStarWarsCharacterSpeciesUseCase(characterDetailsRepositoryMock)
    }

    @Test
    fun `when request for character then get character details`() {
        runBlocking {
            //Given
            val characterId = "https://swapi.py4e.com/api/people/3/"

            //When
            getStarWarsCharacterSpeciesUseCase(characterId)

            //Then
            verify(characterDetailsRepositoryMock).getCharacterSpecies(characterId)
        }
    }
}