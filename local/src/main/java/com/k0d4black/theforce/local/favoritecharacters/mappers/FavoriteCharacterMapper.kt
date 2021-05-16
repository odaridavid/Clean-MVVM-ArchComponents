package com.k0d4black.theforce.local.favoritecharacters.mappers

import com.k0d4black.theforce.local.favoritecharacters.models.FavoriteCharacterEntity
import com.k0d4black.theforce.shared.characters.Character
import com.k0d4black.theforce.shared.planets.Planet
import com.k0d4black.theforce.shared.species.Specie

class FavoriteCharacterMapper {

    //TODO Create room entities for planet and species
    fun mapToDbEntity(favoriteCharacter: Character): FavoriteCharacterEntity =
        with(favoriteCharacter) {
            FavoriteCharacterEntity(
                name = name,
                birthYear = birthYear,
                height = height,
                planetName = planet?.name ?: "Unknown",
                planetPopulation = planet?.population ?: "Unknown",
                specieLanguage = specie?.language ?: "Unknown",
                specieName = specie?.name ?: "Unknown",
                // TODO Flatten list to string json
                filmsJson = ""
            )
        }

    fun mapToDomain(favoriteCharacterEntity: FavoriteCharacterEntity): Character =
        with(favoriteCharacterEntity) {
            Character(
                name = name,
                birthYear = birthYear,
                height = height,
                planet = Planet(name = planetName,population = planetPopulation),
                specie = Specie(name = specieName,language = specieLanguage),
                // TODO Map json string to list
                films = emptyList(),
                // TODO Add URL field in next migration
                url = ""
            )
        }
}
