package com.k0d4black.theforce.remote.character.search

import com.k0d4black.theforce.remote.character.search.data.CharacterSearchApiService
import com.k0d4black.theforce.remote.character.search.data.CharacterSearchRepository
import com.k0d4black.theforce.remote.character.search.data.CharacterSearchRepositoryImpl
import com.k0d4black.theforce.remote.character.search.mappers.CharacterSearchResponseMapper
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
