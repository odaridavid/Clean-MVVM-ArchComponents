package com.k0d4black.theforce.remote.films

import com.k0d4black.theforce.shared.model.Film

class FilmDetailsResponseMapper {

    fun toDomain(filmDetailsResponse: FilmDetailsResponse): Film =
        with(filmDetailsResponse) { Film(title = title, openingCrawl = openingCrawl) }

}
