package com.github.odaridavid.data.local.mappers

import com.github.odaridavid.data.local.models.FavoriteWithFilms
import com.github.odaridavid.data.local.models.FilmEntity
import com.k0d4black.theforce.domain.models.Favorite
import com.k0d4black.theforce.domain.models.Film


internal fun FilmEntity.toDomain(): Film = Film(title, openingCrawl)

internal fun FavoriteWithFilms.toDomain(): Favorite =
    Favorite(
        name = favoriteEntity.name,
        birthYear = favoriteEntity.birthYear,
        height = favoriteEntity.height,
        planetName = favoriteEntity.planetName,
        planetPopulation = favoriteEntity.planetPopulation,
        specieLanguage = favoriteEntity.specieLanguage,
        specieName = favoriteEntity.specieName,
        films = filmEntities.map { it.toDomain() }
    )