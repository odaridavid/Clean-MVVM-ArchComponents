/**
 *
 * Copyright 2020 David Odari
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *          http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 **/
package com.k0d4black.theforce.local.favorites.mappers

import com.k0d4black.theforce.local.favorites.models.FavoriteWithFilms
import com.k0d4black.theforce.local.favorites.models.FilmEntity
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