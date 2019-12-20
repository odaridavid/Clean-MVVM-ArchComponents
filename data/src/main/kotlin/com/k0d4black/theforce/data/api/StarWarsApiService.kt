package com.k0d4black.theforce.data.api

import com.k0d4black.theforce.data.models.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface StarWarsApiService {

    @GET("people")
    suspend fun searchCharacters(@Query("search") params: String): CharacterSearchResponse

    @GET("species/{id}/")
    suspend fun getSpecies(@Path("id") speciesId: Int): SpeciesResponse

    @GET("films/{id}/")
    suspend fun getFilms(@Path("id") filmsId: Int): FilmResponse

    @GET("planets/{id}/")
    suspend fun getPlanet(@Path("id") planetId: Int): PlanetResponse

    @GET("people/{id}/")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): CharacterDetailsResponse
}