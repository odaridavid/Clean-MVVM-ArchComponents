package com.k0d4black.theforce.domain.repository

import com.k0d4black.theforce.domain.models.CharacterDetailsDomainModel


interface CharacterDetailsRepository {

    suspend fun getCharacterDetails(characterId: Int): CharacterDetailsDomainModel?
}

