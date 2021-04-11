package com.k0d4black.theforce.remote.search

import com.k0d4black.theforce.remote.search.data.CharacterSearchApiService
import com.k0d4black.theforce.remote.search.data.CharacterSearchRepository
import com.k0d4black.theforce.remote.search.data.CharacterSearchRepositoryImpl
import com.k0d4black.theforce.remote.search.mappers.CharacterSearchResponseMapper
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteCharacterSearchModule = module {

    single {
        provideCharacterSearchApiService(retrofit = get())
    }

    single<CharacterSearchRepository> {
        CharacterSearchRepositoryImpl(apiService = get(), characterSearchResponseMapper = get())
    }

    single { CharacterSearchResponseMapper() }
}

private fun provideCharacterSearchApiService(retrofit: Retrofit): CharacterSearchApiService =
    retrofit.create(CharacterSearchApiService::class.java)
