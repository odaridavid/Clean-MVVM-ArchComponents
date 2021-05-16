package com.k0d4black.theforce.remote.films

import com.k0d4black.theforce.shared.films.FilmsDataSource
import com.k0d4black.theforce.remote.films.data.FilmsRemoteDataSource
import com.k0d4black.theforce.remote.films.data.FilmsApiService
import com.k0d4black.theforce.remote.films.mappers.FilmResponseMapper
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteFilmsModule = module {

    single {
        provideFilmsApiService(retrofit = get())
    }

    single<FilmsDataSource> {
        FilmsRemoteDataSource(
            apiService = get(),
            filmResponseMapper = get()
        )
    }

    single { FilmResponseMapper() }

}

private fun provideFilmsApiService(retrofit: Retrofit): FilmsApiService =
    retrofit.create(FilmsApiService::class.java)
