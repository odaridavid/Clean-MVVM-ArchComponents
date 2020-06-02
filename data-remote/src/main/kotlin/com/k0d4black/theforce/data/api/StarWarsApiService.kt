package com.k0d4black.theforce.data.api

import com.k0d4black.theforce.data.models.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsApiService {

    @GET("people")
    suspend fun searchCharacters(@Query("search") params: String): SearchResponse

    @GET
    suspend fun getSpecieDetails(@Url speciesUrl: String): SpecieDetailResponse

    @GET
    suspend fun getFilmDetails(@Url filmsUrl: String): FilmDetailResponse

    @GET
    suspend fun getPlanetDetails(@Url planetUrl: String): PlanetDetailsResponse

    @GET
    suspend fun getFilms(@Url characterUrl: String): FilmsResponse

    @GET
    suspend fun getSpecies(@Url characterUrl: String): SpeciesResponse

    @GET
    suspend fun getPlanet(@Url characterUrl: String): PlanetResponse
}