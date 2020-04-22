package com.k0d4black.theforce.data.api

import com.k0d4black.theforce.data.models.response.details.*
import com.k0d4black.theforce.data.models.response.search.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApiService {

    @GET("people")
    suspend fun searchCharacters(@Query("search") params: String): CharacterSearchResponse

    @GET
    suspend fun getSpeciesDetails(@Url speciesUrl: String): SpeciesResponse

    @GET
    suspend fun getFilmDetails(@Url filmsUrl: String): FilmResponse

    @GET
    suspend fun getPlanetDetails(@Url planetUrl: String): PlanetResponse

    @GET
    suspend fun getCharacterFilms(@Url characterUrl: String): CharacterDetailsFilmsResponse

    @GET
    suspend fun getCharacterSpecies(@Url characterUrl: String): CharacterDetailsSpeciesResponse

    @GET
    suspend fun getCharacterHomeworld(@Url characterUrl: String): CharacterDetailsHomeWorldResponse
}