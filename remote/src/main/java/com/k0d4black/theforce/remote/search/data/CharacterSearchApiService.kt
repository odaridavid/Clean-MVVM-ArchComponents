package com.k0d4black.theforce.remote.search.data

import com.k0d4black.theforce.remote.search.models.CharacterSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterSearchApiService {

    @GET("people/")
    suspend fun search(@Query("search") characterName: String): Response<CharacterSearchResponse>
}
