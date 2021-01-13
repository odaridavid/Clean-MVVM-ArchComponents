package com.k0d4black.theforce.local.core.favoritecharacters.mappers

import com.k0d4black.theforce.local.core.favoritecharacters.models.FavoriteCharacterEntity
import com.k0d4black.theforce.shared.model.FavoriteCharacter

class FavoriteCharacterMapper {

    fun toDbEntity(favoriteCharacter: FavoriteCharacter): FavoriteCharacterEntity =
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

    fun toDomain(favoriteCharacterEntity: FavoriteCharacterEntity): FavoriteCharacter =
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