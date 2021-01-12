package com.k0d4black.theforce.remote.films

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface FilmsApiService {

    @GET
    suspend fun getFilmDetails(@Url filmUrl: String): Response<FilmDetailsResponse>
    
    @GET
    suspend fun getFilmUrls(@Url characterUrl: String): Response<FilmUrlsResponse>
}