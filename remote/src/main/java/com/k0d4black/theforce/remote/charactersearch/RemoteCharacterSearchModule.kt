package com.k0d4black.theforce.remote.charactersearch

import com.k0d4black.theforce.remote.charactersearch.data.CharacterSearchApiService
import com.k0d4black.theforce.remote.charactersearch.data.CharacterSearchRepository
import com.k0d4black.theforce.remote.charactersearch.data.CharacterSearchRepositoryImpl
import com.k0d4black.theforce.remote.charactersearch.mappers.CharacterSearchResponseMapper
import org.koin.dsl.module
import retrofit2.Retrofit

internal val remoteCharacterSearchModule = module {

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
