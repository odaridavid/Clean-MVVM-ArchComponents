package com.k0d4black.theforce.remote.character.search.data

import com.k0d4black.theforce.remote.character.search.models.CharacterSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterSearchApiService {

    @GET("people/")
    suspend fun search(@Query("search") characterName: String): Response<CharacterSearchResponse>
}
