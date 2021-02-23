package com.k0d4black.theforce.remote.planet.data

import com.k0d4black.theforce.remote.planet.models.PlanetDetailsResponse
import com.k0d4black.theforce.remote.planet.models.PlanetUrlResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PlanetApiService {

    @GET
    suspend fun getPlanetDetails(@Url planetUrl: String): Response<PlanetDetailsResponse>

    @GET
    suspend fun getPlanetUrl(@Url characterUrl: String): Response<PlanetUrlResponse>
}