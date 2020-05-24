package com.k0d4black.theforce.modules

import com.k0d4black.theforce.data.source.CharacterDetailsRemoteDataSource
import com.k0d4black.theforce.data.source.CharacterSearchRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {

    single { CharacterSearchRemoteDataSource(apiService = get()) }

    single { CharacterDetailsRemoteDataSource(apiService = get()) }
}