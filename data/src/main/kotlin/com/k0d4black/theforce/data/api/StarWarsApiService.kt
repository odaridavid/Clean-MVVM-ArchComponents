package com.k0d4black.theforce.data.api

import com.k0d4black.theforce.data.models.response.details.*
import com.k0d4black.theforce.data.models.response.search.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StarWarsApiService {

    @GET("people")
    suspend fun searchCharacters(@Query("search") params: String): CharacterSearchResponse

    @GET("species/{id}/")
    suspend fun getSpeciesDetails(@Path("id") speciesId: Int): SpeciesResponse

    @GET("films/{id}/")
    suspend fun getFilmDetails(@Path("id") filmsId: Int): FilmResponse

    @GET("planets/{id}/")
    suspend fun getPlanetDetails(@Path("id") planetId: Int): PlanetResponse

    @GET("people/{id}/")
    suspend fun getCharacterFilms(@Path("id") characterId: Int): CharacterDetailsFilmsResponse

    @GET("people/{id}/")
    suspend fun getCharacterSpecies(@Path("id") characterId: Int): CharacterDetailsSpeciesResponse

    @GET("people/{id}/")
    suspend fun getCharacterHomeworld(@Path("id") characterId: Int): CharacterDetailsHomeWorldResponse
}