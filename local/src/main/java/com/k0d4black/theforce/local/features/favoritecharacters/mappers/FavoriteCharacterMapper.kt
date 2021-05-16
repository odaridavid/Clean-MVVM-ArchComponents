package com.k0d4black.theforce.local.features.favoritecharacters.mappers

import com.k0d4black.theforce.local.features.favoritecharacters.models.FavoriteCharacterEntity
import com.k0d4black.theforce.shared.favorites.FavoriteCharacter

class FavoriteCharacterMapper {

    fun mapToDbEntity(favoriteCharacter: FavoriteCharacter): FavoriteCharacterEntity =
        with(favoriteCharacter) {
            FavoriteCharacterEntity(
                name = name,
                birthYear = birthYear,
                height = height,
                planetName = planetName,
                planetPopulation = planetPopulation,
                specieLanguage = specieLanguage,
                specieName = specieName,
                filmsJson = ""
            )
        }

    fun mapToDomain(favoriteCharacterEntity: FavoriteCharacterEntity): FavoriteCharacter =
        with(favoriteCharacterEntity) {
            FavoriteCharacter(
                name = name,
                birthYear = birthYear,
                height = height,
                planetName = planetName,
                planetPopulation = planetPopulation,
                specieLanguage = specieLanguage,
                specieName = specieName,
                films = emptyList()
            )
        }
}
