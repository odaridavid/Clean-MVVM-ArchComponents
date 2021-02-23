package com.k0d4black.theforce.remote.films

import com.k0d4black.theforce.remote.films.data.FilmRepository
import com.k0d4black.theforce.remote.films.data.FilmRepositoryImpl
import com.k0d4black.theforce.remote.films.data.FilmsApiService
import com.k0d4black.theforce.remote.films.mappers.FilmDetailsResponseMapper
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteFilmsModule = module {

    single {
        provideFilmsApiService(retrofit = get())
    }

    single<FilmRepository> {
        FilmRepositoryImpl(
            apiService = get(),
            filmDetailsResponseMapper = get()
        )
    }

    single { FilmDetailsResponseMapper() }

}

private fun provideFilmsApiService(retrofit: Retrofit): FilmsApiService =
    retrofit.create(FilmsApiService::class.java)
