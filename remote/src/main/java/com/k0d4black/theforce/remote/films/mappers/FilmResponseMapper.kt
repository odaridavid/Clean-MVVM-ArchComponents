package com.k0d4black.theforce.remote.films.mappers

import com.k0d4black.theforce.remote.films.models.FilmDetailsResponse
import com.k0d4black.theforce.shared.films.Film

class FilmResponseMapper {

    fun mapToDomain(filmDetailsResponse: FilmDetailsResponse): Film =
        with(filmDetailsResponse) { Film(title = title, openingCrawl = openingCrawl) }

}
