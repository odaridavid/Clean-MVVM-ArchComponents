package com.k0d4black.theforce.remote.charactersearch.data

import com.k0d4black.theforce.remote.charactersearch.models.CharacterSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterSearchApiService {

    @GET("people/")
    suspend fun search(@Query("search") characterName: String): Response<CharacterSearchResponse>
}
